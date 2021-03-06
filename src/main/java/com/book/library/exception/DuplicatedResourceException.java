package com.book.library.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class DuplicatedResourceException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7391706510794339475L;


	public DuplicatedResourceException() {
	}

	public DuplicatedResourceException(String message) {
		super(message);
	}


	public DuplicatedResourceException(String message, Throwable cause) {
		super(message, cause);
	}
}
