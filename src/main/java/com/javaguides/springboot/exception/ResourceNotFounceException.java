package com.javaguides.springboot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/*
 * NB THIS CLASS IS FOR CUSTOM EXCEPTION FOR HANDLING SPRING BOOT DEFAULT ERROR EXCEPTION NOT_FOUND.This class will
 * will catch all resource not found exception that will thrown by the spring boot application.
 */

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFounceException extends RuntimeException{
	
	private String resourceName;
	private String fieldName;
	private Long fieldValue;
	public ResourceNotFounceException(String resourceName, String fieldName, Long fieldValue) {
		//%s -> is a place holder for resource name, field name and field value
		super(String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}
	
	

}
