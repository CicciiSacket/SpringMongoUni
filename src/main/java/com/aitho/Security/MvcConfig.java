package com.aitho.Security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/hello").setViewName("hello");
		registry.addViewController("/login").setViewName("loginController");
	}

	@Override
   	public void addCorsMappings(CorsRegistry registry) {
      registry.addMapping("/**").allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS");
   	}
}