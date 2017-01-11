package com.charlesbishop.webrest.controller;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.charlesbishop.webrest.dao.NeighborhoodDAO;
import com.charlesbishop.webrest.model.Neighborhood;

@Controller
@RequestMapping(value = "/rest/neighborhoods")
public class NeighborhoodController implements BaseController<Neighborhood, String> {

	@RequestMapping(method = RequestMethod.GET)
    public @ResponseBody String list(@RequestParam(required=false) String pageNumber, 
    		@RequestParam(required=false) String perPage) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");		
		NeighborhoodDAO neighborhoodDAO = context.getBean(NeighborhoodDAO.class);
		
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
		
		List<Neighborhood> list = neighborhoodDAO.list(pageNum, perP);
		String returnString = CRUDControllerHelper.generateJSONArray(list);
		context.close();

        return returnString;
    }

	// Get Neighborhoods by ID
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody String get(@PathVariable("id") String id) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");		
		NeighborhoodDAO neighborhoodDAO = context.getBean(NeighborhoodDAO.class);
		
		Neighborhood neighborhood = neighborhoodDAO.get(id);
		context.close();

		try {
			return (neighborhood != null) ? neighborhood.getObjectAsJsonString() : CRUDControllerHelper.EMPTY_JSON_OBJECT;
		}
		catch (Exception e){
			return CRUDControllerHelper.MSG_GENERIC_ERROR;
		}
    }

	/*
	 * Unimplemented methods
	 */
	public String create(Neighborhood obj) {
		return null;
	}
	
	public String update(String id, Neighborhood obj) {
		return null;
	}

	public String delete(String id) {
		return null;
	}

}
