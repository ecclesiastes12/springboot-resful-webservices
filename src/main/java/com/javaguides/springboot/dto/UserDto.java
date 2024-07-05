package com.javaguides.springboot.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//define schema for the api
@Schema(
		description = "UserDto Model Information"
)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

  private Long id;
  
  //User first name should not be null or empty
  @Schema(
			description = " User First Name"
	)
  @NotEmpty(message = "User first name must not be null or empty")
  private String firstName;
  
  //User last name should not be null or empty
  @Schema(
			description = " User Last Name"
	)
  @NotEmpty(message = "User last name must not be null or empty")
  private String lastName;
  
  //User email should not be null or empty
  //Email address should be valid
  @Schema(
			description = " User Email"
	)
  @NotEmpty(message = "User email must not be null or empty")
  @Email(message = "Must provide a valid email")
  private String email;
  // private String emailAddress;
}
