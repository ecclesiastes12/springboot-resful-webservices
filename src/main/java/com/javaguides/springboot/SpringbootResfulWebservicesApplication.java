package com.javaguides.springboot;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringbootResfulWebservicesApplication {

	// CONFIGURE MODELMAPPER CLASS AS A SPRING BEAN FOR MAPPING JPA ENTITY OBJECT TO
	// DTO OBJECT AND
	// VICE VERSA.
	// ONCE CONFIGURED HERE IT CAN BE INJECTED INTO THE SERVICE CLASS WITHOUT USING
	// THE NEW KEY WORD
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringbootResfulWebservicesApplication.class, args);
	}

}
