package com.charlesbishop.webrest.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.charlesbishop.webrest.model.Vacant;
import com.charlesbishop.webrest.util.QueryUtil;

public class VacantDAOImpl implements VacantDAO {

	private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
	public Serializable save(Vacant v) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Serializable id = session.save(v);
		tx.commit();
		session.close();
		return id;
	}

	@SuppressWarnings("unchecked")
	public List<Vacant> list(int pageNumber, int perPage) {
		Session session = this.sessionFactory.openSession();
		Query query = session.createQuery("from Vacant");
		
		// A perPage value of zero indicates to show all records. This is not recommended 
		// due to the size of the dataset
		if (perPage != 0){
			
			// Get the last possible page number
		    String countQ = "Select count (v.referenceID) from Vacant v";
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
		
		List<Vacant> vacantList = query.list();
		session.close();
		return vacantList;
	}
	
	public Class getModelClass() {
		return Vacant.class;
	}

	public Vacant get(String id) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Object vacant = session.get(Vacant.class, id);
		tx.commit();
		session.close();
		return (Vacant) vacant;
	}
	
	public boolean update(Vacant object) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.update(object);
		tx.commit();
		session.close();
		return true;
	}

	public boolean delete(Vacant object) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.delete(object);
		tx.commit();
		session.close();
		return true;
	}

}