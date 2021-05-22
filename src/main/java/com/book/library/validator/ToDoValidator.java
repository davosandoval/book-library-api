package com.book.library.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
public class ToDoValidator implements CustomValidator {
	private static final Logger LOGGER = LoggerFactory.getLogger(ToDoValidator.class);

	@Override
	public boolean supports(Class<?> clazz) {
		//return ToDoDTO.class.equals(clazz);
		return true;
	}

	@Override
	public void validate(Object obj, Errors errors) {
		/*ToDoDTO dto = (ToDoDTO)obj;
		if(checkInputString(dto.getTitle())) {
			LOGGER.info("Title not valid {} ", dto.getTitle());
			errors.rejectValue("title", "title.not.valid");
		}*/
		
	}

}
