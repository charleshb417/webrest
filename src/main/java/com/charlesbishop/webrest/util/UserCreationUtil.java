package com.charlesbishop.webrest.util;

import java.io.Serializable;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.charlesbishop.webrest.dao.AppUserDAO;
import com.charlesbishop.webrest.model.AppUser;

public class UserCreationUtil {

	private final static String ROLE_USER = "USER";
	private static BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	
	public static AppUser createUser(String username, String rawPassword){
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");		
		
		AppUser user = new AppUser();
		user.setUsername(username);
		user.setPassword(encoder.encode(rawPassword));
		user.setUserRole(ROLE_USER);
		
		AppUserDAO userDAO = context.getBean(AppUserDAO.class);
		Serializable id = userDAO.save(user);
		context.close();

		try {
			return (id != null) ? user: null;
		}
		catch (Exception e){
			return null;
		}
	}
}
