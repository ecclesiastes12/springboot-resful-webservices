package com.javaguides.springboot.exception;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 * This class is configure to hold error details that is thrown by spring boot.
 * with is class the custom exception (ResouceNotFound) class created will not take effect
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDetails {

	private LocalDateTime timestamp;
	private String message;
	private String path;
	private String errorCode;
}
