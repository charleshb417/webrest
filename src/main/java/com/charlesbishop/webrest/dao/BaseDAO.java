package com.charlesbishop.webrest.dao;

import java.util.List;

/*
 * This interface provides the basic CRUD operations
 * required for a DAO class.
 * 
 * T is a model class
 */
public interface BaseDAO<T> {

	public void save(T u);
	
	public List<T> list();
	
}
