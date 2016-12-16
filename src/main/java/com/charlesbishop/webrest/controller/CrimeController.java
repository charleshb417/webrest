package com.charlesbishop.webrest.controller;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriComponentsBuilder;

import com.charlesbishop.webrest.dao.CrimeDAO;
import com.charlesbishop.webrest.model.Crime;

@Controller
public class CrimeController implements BaseController<Crime, Integer> {
	
	// Get all Crimes
	@RequestMapping(value = "/rest/crimes", method = RequestMethod.GET)
    public @ResponseBody String list(@RequestParam(required=false) String pageNumber, 
    		@RequestParam(required=false) String perPage) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");		
		CrimeDAO crimeDAO = context.getBean(CrimeDAO.class);
		
		int pageNum = 0;
		int perP = 0;
		if (pageNumber != null && perPage != null){
			try {
				pageNum = Integer.parseInt(pageNumber);
				perP = Integer.parseInt(perPage);
			}
			catch (NumberFormatException e){
				
			}
		}
		
		List<Crime> list = crimeDAO.list(pageNum, perP);
		String returnString = CRUDControllerHelper.generateJSONArray(list);
		context.close();

		try {
	        return returnString;
		}
		catch (Exception e){
			return CRUDControllerHelper.EMPTY_JSON_ARRAY;
		}
    }
	
	// Create a Crime object
	@RequestMapping(value = "/rest/crimes", method = RequestMethod.POST)
	public @ResponseBody String create(@Valid @RequestBody Crime crime) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");		
		CrimeDAO crimeDAO = context.getBean(CrimeDAO.class);
		Serializable id = crimeDAO.save(crime);
		context.close();

		try {
			return (id != null) ? crime.getObjectAsJsonString() : CRUDControllerHelper.MSG_CREATE_FAIL;
		}
		catch (Exception e){
			return CRUDControllerHelper.MSG_CREATE_FAIL;
		}
	}
	
	// Get Crime by ID
	@RequestMapping(value = "/rest/crimes/{id}", method = RequestMethod.GET)
    public @ResponseBody String get(@PathVariable("id") Integer id) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");		
		CrimeDAO crimeDAO = context.getBean(CrimeDAO.class);
		
		Crime crime = crimeDAO.get(id);
		context.close();

		try {
			return (crime != null) ? crime.getObjectAsJsonString() : CRUDControllerHelper.EMPTY_JSON_OBJECT;
		}
		catch (Exception e){
			return CRUDControllerHelper.MSG_GENERIC_ERROR;
		}
    }
	
	// Update a Crime
	@RequestMapping(value = "/rest/crimes/{id}", method = RequestMethod.PUT)
	public @ResponseBody String update(@PathVariable("id") Integer id, @RequestBody Crime crime){
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");		
		CrimeDAO crimeDAO = context.getBean(CrimeDAO.class);
		
		boolean success = crimeDAO.update(crime);
		context.close();

		try {
			return (success) ? crime.getObjectAsJsonString() : CRUDControllerHelper.MSG_UPDATE_FAIL;
		}
		catch (Exception e) {
			return CRUDControllerHelper.MSG_UPDATE_FAIL;
		}
	}
	
	// Delete a Crime by ID
	@RequestMapping(value = "/rest/crimes/{id}", method = RequestMethod.DELETE)
    public @ResponseBody String delete(@PathVariable("id") Integer id) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");		
		CrimeDAO crimeDAO = context.getBean(CrimeDAO.class);
		
		Crime crime = crimeDAO.get(id);
		boolean success = crimeDAO.delete(crime);
		context.close();

		try {
			return (success) ? CRUDControllerHelper.EMPTY_JSON_OBJECT : CRUDControllerHelper.MSG_DELETE_FAIL;	
		}
		catch (Exception e){
			return CRUDControllerHelper.MSG_DELETE_FAIL;
		}
    }
}
