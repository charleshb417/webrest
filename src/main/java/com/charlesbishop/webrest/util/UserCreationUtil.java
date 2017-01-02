package com.charlesbishop.webrest.util;

import java.io.Serializable;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.charlesbishop.webrest.dao.AppUserDAO;
import com.charlesbishop.webrest.model.AppUser;

/**
 * @author charlie
 * This utility class contains helper methods that can assist in the creation of an AppUser object
 */
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
	
	// Check if a password is valid or not
	public static boolean passwordIsValid(String password){
		boolean validLength = (password.length() >= 8 && password.length() <= 14);
		boolean hasSpecial   = !password.matches("[A-Za-z0-9 ]*");
		boolean hasUppercase = !password.equals(password.toLowerCase());
		boolean hasLowercase = !password.equals(password.toUpperCase());
		
		return validLength & hasSpecial & hasUppercase & hasLowercase;
	}
}
