package com.charlesbishop.webrest.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.InMemoryUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.UserDetailsManagerConfigurer.UserDetailsBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.charlesbishop.webrest.dao.AppUserDAO;
import com.charlesbishop.webrest.model.AppUser;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

    	// Get Users from DB
    	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");		
		AppUserDAO userDAO = context.getBean(AppUserDAO.class);
		List<AppUser> list = userDAO.list();
		
		// Iterate through the user list for authentication
        InMemoryUserDetailsManagerConfigurer<AuthenticationManagerBuilder> inMemoryAuth = auth.inMemoryAuthentication();
        inMemoryAuth.passwordEncoder(new BCryptPasswordEncoder());
        for (AppUser user : list)
        	inMemoryAuth.withUser(user.getUsername()).password(user.getPassword()).roles(user.getUserRole());
        		
		context.close();
    }
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	
        http
	        .authorizeRequests()
	        .antMatchers("/resources/**", "/webjars/**", "/login*", "/signup*").permitAll() 
	        .anyRequest().authenticated()
            .and()
            .formLogin()
              .loginPage("/login")
              .defaultSuccessUrl("/")
              .failureUrl("/login?error=true")
            .and()
            .logout().logoutSuccessUrl("/login");
    }
}
