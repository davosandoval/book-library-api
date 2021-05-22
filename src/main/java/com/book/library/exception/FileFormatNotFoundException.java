package com.book.library.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class FileFormatNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2827688087769613392L;

	public FileFormatNotFoundException() {
	}

	public FileFormatNotFoundException(String message) {
		super(message);
	}


	public FileFormatNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
