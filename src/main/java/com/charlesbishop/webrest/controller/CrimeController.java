package com.charlesbishop.webrest.controller;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.charlesbishop.webrest.dao.CrimeDAO;
import com.charlesbishop.webrest.model.Crime;

@Controller
public class CrimeController {
	
	@RequestMapping(value = "/rest/crimes", method = RequestMethod.GET)
    public @ResponseBody String get() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");		
		CrimeDAO crimeDAO = context.getBean(CrimeDAO.class);
		
		List<Crime> list = crimeDAO.list();
		String returnString = CRUDControllerHelper.generateJSONArray(list);
		context.close();

        return returnString;
    }
	
}
