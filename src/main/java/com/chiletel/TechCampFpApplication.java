package com.chiletel;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TechCampFpApplication {

	public static void main(String[] args) {
		SpringApplication.run(TechCampFpApplication.class, args);
	}
	
	/**
	 * Configuraci�n modelMapper
	 * @return
	 */
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
		
	
	
	
	
}
