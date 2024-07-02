//package com.javaguides.springboot.exception;
//
//import java.time.LocalDateTime;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.context.request.WebRequest;
//
//@ControllerAdvice //is used to handle both specific error exception and global exception in a single place
//public class GlobalExceptionHandler2 {
//
//	
//	//implementation of custom exception(specific exception) in respect of the controller method
//	  @ExceptionHandler(ResourceNotFounceException.class)// @ExceptionHandler is used to handle specific exception and return the response back to the client
//	  public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFounceException exception, WebRequest webRequest){
//		  
//		  //error detail object
//		  ErrorDetails errorDetails = new ErrorDetails(
//				  
//				  LocalDateTime.now(), //the time error is generated
//				  exception.getMessage(), // get the error message generated
//				  
//				  /*Get a short description of this request,typically containing request URI and session id.
//				   * Parameters:includeClientInfo whether to include client-specificinformation such as session id and user name
//				  */
//				  webRequest.getDescription(false),
//				  "USER_NOT_FOUND"
//				  );
//		  
//		  return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);
//	  }
//	  
//	  
//	  //implementatio of EmailAlreadyExistException in the globalexception 
//	  @ExceptionHandler(EmailAlreadyExistException.class)// @ExceptionHandler is used to handle specific exception and return the response back to the client
//	  public ResponseEntity<ErrorDetails> handleResourceNotFoundException(EmailAlreadyExistException exception, WebRequest webRequest){
//		  
//		  //error detail object
//		  ErrorDetails errorDetails = new ErrorDetails(
//				  
//				  LocalDateTime.now(), //the time error is generated
//				  exception.getMessage(), // get the error message generated
//				  
//				  /*Get a short description of this request,typically containing request URI and session id.
//				   * Parameters:includeClientInfo whether to include client-specificinformation such as session id and user name
//				  */
//				  webRequest.getDescription(false),
//				  "USER_EMAIL_ALREADY_EXIST"
//				  );
//		  
//		  return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.BAD_REQUEST);
//	  }
//	  
//	  
//	//THIS EXCEPTION IS TO HANDLE ANY OTHER FORM OF ERROR THAT MAY OCCURE APART FROM ResourceNotFounceException AND EmailAlreadyExistException
//	  @ExceptionHandler(Exception.class)// @ExceptionHandler is used to handle specific exception and return the response back to the client
//	  public ResponseEntity<ErrorDetails> handleResourceNotFoundException(Exception exception, WebRequest webRequest){
//		  
//		  //error detail object
//		  ErrorDetails errorDetails = new ErrorDetails(
//				  
//				  LocalDateTime.now(), //the time error is generated
//				  exception.getMessage(), // get the error message generated
//				  
//				  /*Get a short description of this request,typically containing request URI and session id.
//				   * Parameters:includeClientInfo whether to include client-specificinformation such as session id and user name
//				  */
//				  webRequest.getDescription(false),
//				  "INTERNAL SERVER ERROR"
//				  );
//		  
//		  return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
//	  }
//}
