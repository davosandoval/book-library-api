package com.book.library.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
public class NotLegalStateException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7391706510794339475L;


	public NotLegalStateException() {
	}

	public NotLegalStateException(String message) {
		super(message);
	}


	public NotLegalStateException(String message, Throwable cause) {
		super(message, cause);
	}
}
