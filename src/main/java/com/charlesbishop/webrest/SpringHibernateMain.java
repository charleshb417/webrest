package com.charlesbishop.webrest;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.charlesbishop.webrest.dao.CrimeDAO;
import com.charlesbishop.webrest.model.Crime;

/*
 * Quick testing class
 * TODO move to JUnit
 */
public class SpringHibernateMain {

	public static void main(String[] args) {

		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
		
		CrimeDAO crimeDAO = context.getBean(CrimeDAO.class);
		
		List<Crime> list = crimeDAO.list();
		
		for(Crime c : list){
			System.out.println(c);
		}
		//close resources
		context.close();	
	}
}
