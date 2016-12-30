package com.charlesbishop.webrest.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.charlesbishop.webrest.model.Neighborhood;
import com.charlesbishop.webrest.util.QueryUtil;

public class NeighborhoodDAOImpl implements NeighborhoodDAO {

	private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
	@SuppressWarnings("unchecked")
	public List<Neighborhood> list(int pageNumber, int perPage) {
		Session session = this.sessionFactory.openSession();		
		Query query = session.createQuery("from Neighborhood");
		
		// A perPage value of zero indicates to show all records. This is not recommended 
		// due to the size of the dataset
		if (perPage != 0){
			
			// Get the last possible page number
		    String countQ = "Select count (n.neighborhood) from Neighborhood n";
		    Query countQuery = session.createQuery(countQ);
		    Long countResults = (Long) countQuery.uniqueResult();
			int lastPageNumber = QueryUtil.calculateLastPageNumber(countResults, perPage);
			
		    // If we are on or beyond the last page, set the first result accordingly
		    int firstResult;
		    if (pageNumber >= lastPageNumber)
		    	firstResult = (lastPageNumber - 1) * perPage;
		    else
		    	firstResult = (pageNumber * perPage);
			
			query.setFirstResult(firstResult);
			query.setMaxResults(perPage);	
		}
		
		List<Neighborhood> neighborhoodList = query.list();
		session.close();
		return neighborhoodList;
	}

	public Neighborhood get(String id) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Object neighborhood = session.get(Neighborhood.class, id);
		tx.commit();
		session.close();
		return (Neighborhood) neighborhood;
	}

	public Class<Neighborhood> getModelClass() {
		return Neighborhood.class;
	}

}
