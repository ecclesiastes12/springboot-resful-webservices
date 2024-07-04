package com.javaguides.springboot.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.javaguides.springboot.dto.UserDto;
import com.javaguides.springboot.entity.User;
import com.javaguides.springboot.exception.ErrorDetails;
import com.javaguides.springboot.exception.ResourceNotFounceException;
import com.javaguides.springboot.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

/*
 * CODE REFACTORED TO USE DTO SEND INFORMATION BETWEEN THE CLIENT AND THE SERVER.
 *  CHECK UserController1.java for the previous code
 *  
 *  
 *  @Tag provides description about this controller 
 *  @Operation - The annotation may be used to define a resource method as an OpenAPI Operation, 
 *  and/or to define additionalproperties for the Operation
 *  
 *  @ApiResponse - provides information about api response
 */


@Tag(
	name = "CRUD REST APIs for User Resource",
	description = "CRUD REST APIS - Create User, Update User, Get User, Get All Users, Delete User"
)
@RestController
@AllArgsConstructor
@RequestMapping("api/users")
public class UserController {

  private UserService userService;

  //code refactor with @Valid annotation
  // build create User REST API
  @Operation(
		  summary = "Create User REST API",
		  description = "Create User REST API is used to save user in a database "
  )
  @ApiResponse(
	   responseCode = "201",
	   description = "HTTP Status 201 CREATED"
  )
  @PostMapping
  public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto user) {

    UserDto savedUser = userService.createUser(user);
    return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
  }

  // build get User by id REST API
  // http://localhost:8080/api/users/1
  @Operation(
		  summary = "Get User By ID REST API",
		  description = "Get User By ID  REST API is used get a single user from the database "
  )
  @ApiResponse(
	   responseCode = "200",
	   description = "HTTP Status 200 SUCCESS"
  )
  @GetMapping("{id}")
  public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long userId) {
    UserDto user = userService.getUserById(userId);
    return new ResponseEntity<>(user, HttpStatus.OK);
  }

  // build get all Users REST API
  @Operation(
		  summary = "Get All Users REST API",
		  description = "Get All Users REST API is used get all users from the database "
  )
  @ApiResponse(
	   responseCode = "200",
	   description = "HTTP Status 200 SUCCESS"
  )
  @GetMapping
  public ResponseEntity<List<UserDto>> getAllUsers() {
    List<UserDto> users = userService.getAllUsers();
    return new ResponseEntity<>(users, HttpStatus.OK);
  }

  // build Update Users REST API
  // http://localhost:8080/api/users/1
  @Operation(
		  summary = "Update User By ID REST API",
		  description = "Update User By ID  REST API is used update a particular user in the database "
  )
  @ApiResponse(
	   responseCode = "200",
	   description = "HTTP Status 200 SUCCESS"
  )
  @PutMapping("{id}")
  public ResponseEntity<UserDto> updateUser(@PathVariable("id") Long userId,
       @RequestBody @Valid UserDto user) {
    // set user id
    user.setId(userId);
    UserDto updatedUser = userService.updateUser(user);
    return new ResponseEntity<>(updatedUser, HttpStatus.OK);
  }

  // build Delete Users REST API
  // http://localhost:8080/api/users/1
  @Operation(
		  summary = "Delete User By ID REST API",
		  description = "Delete User By ID  REST API is used delete a particula user from the database "
  )
  @ApiResponse(
	   responseCode = "200",
	   description = "HTTP Status 200 SUCCESS"
  )
  @DeleteMapping("{id}")
  public ResponseEntity<String> deleteUser(@PathVariable("id") Long userId) {
    userService.deleteUser(userId);
    return new ResponseEntity<>("User successfully deleted!", HttpStatus.OK);
  }

  
    //code moved to global exception
//  //implementation of custom exception(specific exception) in respect of the controller method
//  @ExceptionHandler(ResourceNotFounceException.class)// @ExceptionHandler is used to handle specific exception and return the response back to the client
//  public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFounceException exception, WebRequest webRequest){
//	  
//	  //error detail object
//	  ErrorDetails errorDetails = new ErrorDetails(
//			  
//			  LocalDateTime.now(), //the time error is generated
//			  exception.getMessage(), // get the error message generated
//			  
//			  /*Get a short description of this request,typically containing request URI and session id.
//			   * Parameters:includeClientInfo whether to include client-specificinformation such as session id and user name
//			  */
//			  webRequest.getDescription(false),
//			  "USER_NOT_FOUND"
//			  );
//	  
//	  return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);
//  }
}
