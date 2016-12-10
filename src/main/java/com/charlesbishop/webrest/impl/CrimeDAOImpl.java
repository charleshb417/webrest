package com.charlesbishop.webrest.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.charlesbishop.webrest.dao.CrimeDAO;
import com.charlesbishop.webrest.model.Crime;

public class CrimeDAOImpl implements CrimeDAO {

	private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
	public void save(Crime c) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.persist(c);
		tx.commit();
		session.close();
	}

	@SuppressWarnings("unchecked")
	public List<Crime> list() {
		Session session = this.sessionFactory.openSession();
		List<Crime> personList = session.createQuery("from Crime").list();
		session.close();
		return personList;
	}

	public Class getModelClass() {
		return Crime.class;
	}

}