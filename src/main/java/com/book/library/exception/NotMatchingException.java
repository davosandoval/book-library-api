package com.book.library.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class NotMatchingException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6604094988906348398L;
	
	public NotMatchingException() {
	}

	public NotMatchingException(String message) {
		super(message);
	}


	public NotMatchingException(String message, Throwable cause) {
		super(message, cause);
	}

}
