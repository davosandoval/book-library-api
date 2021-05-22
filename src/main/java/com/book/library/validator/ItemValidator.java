package com.book.library.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
public class ItemValidator implements CustomValidator {
	private static final Logger LOGGER = LoggerFactory.getLogger(ItemValidator.class);

	@Override
	public boolean supports(Class<?> clazz) {
		//return ItemDTO.class.equals(clazz);
		return true;
	}

	@Override
	public void validate(Object obj, Errors errors) {
		/*ItemDTO dto = (ItemDTO)obj;
		if(checkInputString(dto.getDescription())) {
			LOGGER.info("Description not valid {} ", dto.getDescription());
			errors.rejectValue("description", "descrption.not.valid");
		}
		
		if(Objects.isNull(dto.getStatus()) || checkInputString(dto.getStatus().toString())) {
			LOGGER.info("Status not valid {} ", dto.getDescription());
			errors.rejectValue("status", "status.not.valid");
		}*/
		
	}

}
