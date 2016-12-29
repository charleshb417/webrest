package com.charlesbishop.webrest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.charlesbishop.webrest.config.authentication.AuthenticationService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	AuthenticationService authenticationService;
	
	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(authenticationService).passwordEncoder(new BCryptPasswordEncoder());
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
