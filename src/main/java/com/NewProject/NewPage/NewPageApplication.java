package com.NewProject.NewPage;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class NewPageApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewPageApplication.class, args);
	}

	@Bean
	public ModelMapper getModelMapper()
	{
		return  new ModelMapper();
	}
}

// @Bean can be use in config file.. means this is used only use in application.java file