package com.charlesbishop.webrest.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.charlesbishop.webrest.model.AppUser;

public class AppUserDAO implements BaseDAO<AppUser, String> {
	
	private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
	@SuppressWarnings("unchecked")
	public List<AppUser> list() {
		Session session = this.sessionFactory.openSession();
		List<AppUser> userList = session.createQuery("from AppUser").list();
		session.close();
		return userList;
	}
	
	public Serializable save(AppUser u) {
		try {
			Session session = this.sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			Serializable id = session.save(u);
			tx.commit();
			session.close();
			return id;	
		}
		catch (Exception e){
			return null;
		}
	}
	
	public AppUser get(String username) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Object user = session.get(AppUser.class, username);
		tx.commit();
		session.close();
		return (AppUser) user;
	}


	public boolean update(AppUser object) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.update(object);
		tx.commit();
		session.close();
		return true;
	}

	public boolean delete(AppUser object) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.delete(object);
		tx.commit();
		session.close();
		return true;
	}
	
	// Pagination not neccessary for Users
	public List<AppUser> list(int pageNumber, int perPage) {
		return list();
	}

	public Class<AppUser> getModelClass() {
		return AppUser.class;
	}
}
