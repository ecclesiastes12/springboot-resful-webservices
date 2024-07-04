package com.javaguides.springboot;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

//@OpenAPIDefinition annotation is used to provide general information about the rest api 

@SpringBootApplication
@OpenAPIDefinition(
	info = @Info(
			title = "Spring Boot REST API Documentation",
			description = "Spring Boot REST API Doucmentation",
			version = "v1.0",
			contact = @Contact(
					name = "Solomon",
					email = "solomonotoo74@gmail.com",
					url = "https://www.ecclinks.com"
			),
			//license information
			license = @License(
					name = "Apache 2.0",
					url = "https://www.ecclinks.com/license"
			)
			
	),
	//external documentation
	externalDocs = @ExternalDocumentation(
			description = "Spring Boot User Management Document",
			url = "https://www.ecclinks.com/user_management.html"
	)
)
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
