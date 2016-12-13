package com.charlesbishop.webrest.controller;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.charlesbishop.webrest.dao.VacantDAO;
import com.charlesbishop.webrest.model.Vacant;

@Controller
public class VacantController implements BaseController<Vacant, String>{
	
	@RequestMapping(value = "/rest/vacants", method = RequestMethod.GET)
    public @ResponseBody String list() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");		
		VacantDAO vacantDAO = context.getBean(VacantDAO.class);
		
		List<Vacant> list = vacantDAO.list();
		String returnString = CRUDControllerHelper.generateJSONArray(list);
		context.close();

        return returnString;
    }
	
	// Create a Vacant object
	@RequestMapping(value = "/rest/vacants", method = RequestMethod.POST)
	public @ResponseBody String create(@Valid @RequestBody Vacant vacant) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");		
		VacantDAO vacantDAO = context.getBean(VacantDAO.class);
		Serializable id = vacantDAO.save(vacant);
		context.close();

		try {
			return (id != null) ? vacant.getObjectAsJsonString() : CRUDControllerHelper.MSG_CREATE_FAIL;
		}
		catch (Exception e){
			return CRUDControllerHelper.MSG_CREATE_FAIL;
		}
	}
	
	// Get Vacant by ID
	@RequestMapping(value = "/rest/vacants/{id}", method = RequestMethod.GET)
    public @ResponseBody String get(@PathVariable("id") String id) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");		
		VacantDAO vacantDAO = context.getBean(VacantDAO.class);
		
		Vacant vacant = vacantDAO.get(id);
		context.close();

		try {
			return (vacant != null) ? vacant.getObjectAsJsonString() : CRUDControllerHelper.EMPTY_JSON_OBJECT;
		}
		catch (Exception e){
			return CRUDControllerHelper.MSG_GENERIC_ERROR;
		}
    }
	
	// Update a Vacant
	@RequestMapping(value = "/rest/vacants/{id}", method = RequestMethod.PUT)
	public @ResponseBody String update(@PathVariable("id") String id, @RequestBody Vacant vacant){
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");		
		VacantDAO vacantDAO = context.getBean(VacantDAO.class);
		
		boolean success = vacantDAO.update(vacant);
		context.close();

		try {
			return (success) ? vacant.getObjectAsJsonString() : CRUDControllerHelper.MSG_UPDATE_FAIL;
		}
		catch (Exception e) {
			return CRUDControllerHelper.MSG_UPDATE_FAIL;
		}
	}
	
	// Delete a Vacant by ID
	@RequestMapping(value = "/rest/vacants/{id}", method = RequestMethod.DELETE)
    public @ResponseBody String delete(@PathVariable("id") String id) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");		
		VacantDAO vacantDAO = context.getBean(VacantDAO.class);
		
		Vacant vacant = vacantDAO.get(id);
		boolean success = vacantDAO.delete(vacant);
		context.close();

		try {
			return (success) ? CRUDControllerHelper.EMPTY_JSON_OBJECT : CRUDControllerHelper.MSG_DELETE_FAIL;	
		}
		catch (Exception e){
			return CRUDControllerHelper.MSG_DELETE_FAIL;
		}
    }
}
