package com.charlesbishop.webrest.controller;

import com.charlesbishop.webrest.model.ObjModel;

public interface BaseController<T extends ObjModel, U> {

	public String list();
	public String create(T obj);
	public String get(U id);
	public String update(U id, T obj);
	public String delete(U id);
}
