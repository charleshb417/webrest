package com.charlesbishop.webrest.util;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.charlesbishop.webrest.dao.AppUserDAO;
import com.charlesbishop.webrest.model.AppUser;

public class UserCreationUtilTest {

	@Test
	public void testPasswordIsHashedAndNotPlaintext(){
		boolean pass = true;
		
		String username = "testUser";
		String password = "testPassword";
		
		AppUser createdUser = UserCreationUtil.createUser(username, password);
		String newPassword = createdUser.getPassword();
		
		// Check that they are not equal
		if (newPassword.equals(password))
			pass = false;
		
		//Delete the test user
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");		
		AppUserDAO userDAO = context.getBean(AppUserDAO.class);
		userDAO.delete(createdUser);
		context.close();
		
		if (!pass){
			fail();
		}
		
	}
}
