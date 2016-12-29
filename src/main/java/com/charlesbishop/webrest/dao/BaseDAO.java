package com.charlesbishop.webrest.dao;

import java.io.Serializable;
import java.util.List;

/*
 * This interface provides the basic CRUD operations
 * required for a DAO class.
 * 
 * T is a model class
 * U is the class for the Primary Key
 */
public interface BaseDAO<T, U> {

	// Create an object and retrieve the ID
	public Serializable save(T u);
	
	// Get a list of objects; allow for pagination
	public List<T> list(int pageNumber, int perPage);
		
	// Get an object by its ID
	public T get(U id);
	
	// Update an object
	public boolean update(T object);
	
	// Delete an object
	public boolean delete(T object);
	
	// Provide a means to retrieve the Model class from a DAO class
	public Class getModelClass();
}
