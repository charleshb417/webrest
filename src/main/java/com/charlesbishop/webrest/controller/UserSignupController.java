package com.charlesbishop.webrest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.charlesbishop.webrest.model.AppUser;
import com.charlesbishop.webrest.util.UserCreationUtil;

@Controller
public class UserSignupController {

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String list(@RequestParam("username") String username, @RequestParam("password") String password, Model model){
    	
		String signupPage = "signup";
		String homePage = "login";
		
		// Check that all parameters are present
		if (username == null || password == null || username.length() == 0 || password.length() == 0){
			model.addAttribute("error", "Your request was incomplete.");
			return signupPage;	
		}
		
		// Check that password matches requirements 
		if (!UserCreationUtil.passwordIsValid(password)){
			model.addAttribute("error", "Your password does not meet the requirements.");
			return signupPage;
		}
		
		AppUser user = UserCreationUtil.createUser(username, password);
		
		if (user != null){
			return homePage;
		}
		else {
			model.addAttribute("error", "That username is taken already.");
			return signupPage;
		}
    }
}
