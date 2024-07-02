package com.javaguides.springboot.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

//NB ResponseEntityExceptionHandler class contains the method for handling specific exceptions like validation exceptions
@ControllerAdvice //is used to handle both specific error exception and global exception in a single place
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{

	
	//implementation of custom exception(specific exception) in respect of the controller method
	  @ExceptionHandler(ResourceNotFounceException.class)// @ExceptionHandler is used to handle specific exception and return the response back to the client
	  public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFounceException exception, WebRequest webRequest){
		  
		  //error detail object
		  ErrorDetails errorDetails = new ErrorDetails(
				  
				  LocalDateTime.now(), //the time error is generated
				  exception.getMessage(), // get the error message generated
				  
				  /*Get a short description of this request,typically containing request URI and session id.
				   * Parameters:includeClientInfo whether to include client-specificinformation such as session id and user name
				  */
				  webRequest.getDescription(false),
				  "USER_NOT_FOUND"
				  );
		  
		  return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);
	  }
	  
	  
	  //implementatio of EmailAlreadyExistException in the globalexception 
	  @ExceptionHandler(EmailAlreadyExistException.class)// @ExceptionHandler is used to handle specific exception and return the response back to the client
	  public ResponseEntity<ErrorDetails> handleResourceNotFoundException(EmailAlreadyExistException exception, WebRequest webRequest){
		  
		  //error detail object
		  ErrorDetails errorDetails = new ErrorDetails(
				  
				  LocalDateTime.now(), //the time error is generated
				  exception.getMessage(), // get the error message generated
				  
				  /*Get a short description of this request,typically containing request URI and session id.
				   * Parameters:includeClientInfo whether to include client-specificinformation such as session id and user name
				  */
				  webRequest.getDescription(false),
				  "USER_EMAIL_ALREADY_EXIST"
				  );
		  
		  return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.BAD_REQUEST);
	  }
	  
	  
	//THIS EXCEPTION IS TO HANDLE ANY OTHER FORM OF ERROR THAT MAY OCCURE APART FROM ResourceNotFounceException AND EmailAlreadyExistException
	  @ExceptionHandler(Exception.class)// @ExceptionHandler is used to handle specific exception and return the response back to the client
	  public ResponseEntity<ErrorDetails> handleResourceNotFoundException(Exception exception, WebRequest webRequest){
		  
		  //error detail object
		  ErrorDetails errorDetails = new ErrorDetails(
				  
				  LocalDateTime.now(), //the time error is generated
				  exception.getMessage(), // get the error message generated
				  
				  /*Get a short description of this request,typically containing request URI and session id.
				   * Parameters:includeClientInfo whether to include client-specificinformation such as session id and user name
				  */
				  webRequest.getDescription(false),
				  "INTERNAL SERVER ERROR"
				  );
		  
		  return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	  }


	  //method for handling validation exception
	  @Override
		protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
				HttpHeaders headers, HttpStatusCode status, WebRequest request) {
			// Map is used to store multiple validation error messages in object
		  	Map<String, String> errors = new HashMap<>();
		  	
		  	//list all the errors
		  	List<ObjectError> errorList = ex.getBindingResult().getAllErrors();
		  	
		  	//iterate over the list of errors
		  	errorList.forEach((error) ->{
		  		//get the field variable name. thus the validation field name 
		  		String fieldName = ((FieldError) error).getField();
		  		
		  		//get validation error message
		  		String message = error.getDefaultMessage();
		  		
		  		//add the validation fieldName and message to the map
		  		errors.put(fieldName, message);
		  	} );
		  	
		  	
		  	return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
		   
		}
	  
}
