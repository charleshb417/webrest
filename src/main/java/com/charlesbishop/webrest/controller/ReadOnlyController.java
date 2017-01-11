package com.charlesbishop.webrest.controller;

import com.charlesbishop.webrest.model.ObjModel;

/*
 * This interface provides the required READ ONLY REST operations
 * (LIST, GET)
 * 
 * T is a model class
 * U is the class for the Primary Key
 */
public interface ReadOnlyController<T extends ObjModel, U> {
	public String list(String pageNumber, String perPage);
	public String get(U id);
}
