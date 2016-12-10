package com.charlesbishop.webrest.controller;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.charlesbishop.webrest.dao.VacantDAO;
import com.charlesbishop.webrest.model.Vacant;

@Controller
public class VacantController {
	
	@RequestMapping(value = "/rest/vacants", method = RequestMethod.GET)
    public @ResponseBody String get() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");		
		VacantDAO vacantDAO = context.getBean(VacantDAO.class);
		
		List<Vacant> list = vacantDAO.list();
		String returnString = CRUDControllerHelper.generateJSONArray(list);
		context.close();

        return returnString;
    }
	
}
