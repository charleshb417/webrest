package com.charlesbishop.webrest.config.authentication;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.charlesbishop.webrest.controller.CRUDControllerHelper;
import com.charlesbishop.webrest.dao.AppUserDAO;
import com.charlesbishop.webrest.dao.CrimeDAO;
import com.charlesbishop.webrest.model.AppUser;
import com.charlesbishop.webrest.model.Crime;

@Service
public class AuthenticationService implements UserDetailsService {

	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");		
		AppUserDAO userDAO = context.getBean(AppUserDAO.class);
		
		AppUser user = userDAO.get(username);
		context.close();

		try {
			if (user != null){
				GrantedAuthority authority = new SimpleGrantedAuthority(user.getUserRole());
				UserDetails userDetails = (UserDetails) new User(user.getUsername(), 
						user.getPassword(), Arrays.asList(authority));
				
				return userDetails;	
			}
			else {
				return null;
			}
		}
		catch (Exception e){
			return null;
		}
	}

}
