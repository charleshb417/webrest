package com.charlesbishop.webrest.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.charlesbishop.webrest.model.Crime;
import com.charlesbishop.webrest.model.Vacant;

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
	public List<Vacant> list() {
		Session session = this.sessionFactory.openSession();
		List<Vacant> vacantList = session.createQuery("from Vacant").list();
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