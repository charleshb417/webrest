package com.charlesbishop.webrest.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.charlesbishop.webrest.model.Crime;

public class CrimeDAOImpl implements CrimeDAO {

	private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
	public Serializable save(Crime c) {
		System.out.println(c.getObjectAsJsonString());
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Serializable id = session.save(c);
		tx.commit();
		session.close();
		return id;
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

	public Crime get(Integer id) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Object crime = session.get(Crime.class, id);
		tx.commit();
		session.close();
		return (Crime) crime;
	}

	public boolean update(Crime object) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.update(object);
		tx.commit();
		session.close();
		return true;
	}

	public boolean delete(Crime object) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.delete(object);
		tx.commit();
		session.close();
		return true;
	}

}