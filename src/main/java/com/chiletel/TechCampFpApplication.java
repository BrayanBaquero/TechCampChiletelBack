package com.chiletel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;


import com.chiletel.entity.Cliente;
import com.chiletel.mapper.MapperData;

@SpringBootApplication
public class TechCampFpApplication {

	public static void main(String[] args) {
		SpringApplication.run(TechCampFpApplication.class, args);
	}
	
	
	@Bean
	public MapperData modelMapper() {
		return new MapperData() {
			/*
			@Override
			public ClienteDTO clienteToDto(Cliente cliente) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Cliente DtoToCliente(ClienteDTO clienteDTO) {
				// TODO Auto-generated method stub
				return null;
			}
			*/
		};
		
	}
	
	
}
