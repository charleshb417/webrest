package com.charlesbishop.webrest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@EnableWebMvc
@Configuration
@ComponentScan({ "com.charlesbishop.webrest" })
public class SpringWebConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/webrest/resources/static/js/**")
				.addResourceLocations("/webrest/resources/static/js/");
		registry.addResourceHandler("/webrest/resources/static/css/**")
				.addResourceLocations("/webrest/resources/static/css/");
		registry.addResourceHandler("/webrest/resources/static/views/**")
				.addResourceLocations("/webrest/resources/static/views/");
		registry.addResourceHandler("/webrest/resources/static/**")
				.addResourceLocations("/webrest/resources/static/");
		registry.addResourceHandler("/webrest/webjars/**").addResourceLocations(
				"/webjars/");
	}

	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/jsp/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
}
