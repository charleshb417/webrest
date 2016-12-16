package com.charlesbishop.webrest.controller;

import com.charlesbishop.webrest.model.ObjModel;

/*
 * This interface provides the basic required REST operations
 * 
 * T is a model class
 * U is the class for the Primary Key
 */
public interface BaseController<T extends ObjModel, U> {

	public String list(String pageNumber, String perPage);
	public String create(T obj);
	public String get(U id);
	public String update(U id, T obj);
	public String delete(U id);
}
