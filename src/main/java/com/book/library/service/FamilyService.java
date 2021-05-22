package com.book.library.service;

import java.util.List;

import com.book.library.dto.FamilyDTO;

public interface FamilyService {
	List<FamilyDTO> getAllFamilies();
	FamilyDTO insertOrUpdateFamily(FamilyDTO familyDTO);
	FamilyDTO getFamilyById(Integer id);
}
