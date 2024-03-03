package com.medibank.posts.postsWebService.advice;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.medibank.posts.postsWebService.exception.InternalServerException;
import com.medibank.posts.postsWebService.exception.TypiCodePostNotFoundException;

/** Handle Exception Globally */
@ControllerAdvice
public class HandleGlobalException extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = TypiCodePostNotFoundException.class)
	public ResponseEntity<String> resourceNotFoundException(TypiCodePostNotFoundException ex, WebRequest request) {
		return new ResponseEntity<String>("Post Not Found", new HttpHeaders(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = InternalServerException.class)
	public ResponseEntity<String> InternalServerException(InternalServerException ex, WebRequest request) {
		return new ResponseEntity<String>("Internal Server Error, Please try again later.", new HttpHeaders(),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
