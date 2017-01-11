package com.charlesbishop.webrest.controller;

import com.charlesbishop.webrest.model.ObjModel;

/*
 * This interface provides the required REST operations
 * in addition to those of ReadOnlyController
 * 
 * T is a model class
 * U is the class for the Primary Key
 */
public interface BaseController<T extends ObjModel, U> extends ReadOnlyController<T, U>{
	public String create(T obj);
	public String update(U id, T obj);
	public String delete(U id);
}
