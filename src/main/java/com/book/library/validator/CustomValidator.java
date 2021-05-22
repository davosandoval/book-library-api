package com.book.library.validator;

import org.springframework.validation.Validator;

public interface CustomValidator extends Validator {
	default boolean checkInputString(String input) {
        return (input == null || input.trim().length() == 0);
    }
}
