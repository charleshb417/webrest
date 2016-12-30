package com.charlesbishop.webrest.dao;

import java.util.List;

/*
 * This interface provides the list and GET operations
 * required for a View DAO class.
 * 
 * T is a model class
 * U is the class for the Primary Key
 */
public interface ViewDAO<T, U> {

	// Get a list of objects; allow for pagination
	public List<T> list(int pageNumber, int perPage);
		
	// Get an object by its ID
	public T get(U id);
	
	// Provide a means to retrieve the Model class from a DAO class
	public Class getModelClass();
}
