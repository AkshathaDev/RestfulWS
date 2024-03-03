package com.medibank.posts.postsWebService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Post Not Found")
public class TypiCodePostNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3829503995981562468L;

	public TypiCodePostNotFoundException(HttpClientErrorException e) {
	}
	
	public TypiCodePostNotFoundException(HttpClientErrorException e, String message) {
		super(message);
	}

	public TypiCodePostNotFoundException() {
	}

}
