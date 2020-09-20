package com.webvirtua.patrimony;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class StartApplication 
{
	@Bean
	public ModelMapper modelMapper() {
		// instancia singleton modelMapper que pode ser usada em toda a aplicação
		return new ModelMapper();
	}
	
	public static void main(String[] args) 
	{
		SpringApplication.run(StartApplication.class, args);
	}
}