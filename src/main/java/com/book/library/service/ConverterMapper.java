package com.book.library.service;

import com.book.library.dto.BaseDTO;
import com.book.library.entity.BaseEntity;

public interface ConverterMapper<U extends BaseDTO, V extends BaseEntity> {
	U convertToDto(V entity);
	V convertToEntity(U dto);
}
