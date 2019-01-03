package com.cgi.java.FilRouge.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan("com.cgi.java")
@EnableJpaRepositories("com.cgi.java.FilRouge.repository") 
@EntityScan("com.cgi.java.FilRouge.model")
@SpringBootApplication
public class Application extends SpringBootServletInitializer{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		SpringApplication.run(Application.class, args);
	}

}
