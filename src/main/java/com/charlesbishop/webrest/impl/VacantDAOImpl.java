package com.charlesbishop.webrest.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.charlesbishop.webrest.dao.VacantDAO;
import com.charlesbishop.webrest.model.Vacant;

public class VacantDAOImpl implements VacantDAO {

	private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
	public void save(Vacant v) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.persist(v);
		tx.commit();
		session.close();
	}

	@SuppressWarnings("unchecked")
	public List<Vacant> list() {
		Session session = this.sessionFactory.openSession();
		List<Vacant> vacantList = session.createQuery("from Vacant").list();
		session.close();
		return vacantList;
	}
	
	public Class getModelClass() {
		return Vacant.class;
	}

}