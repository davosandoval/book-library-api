package com.book.library.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.book.library.dto.FamilyDTO;
import com.book.library.entity.Family;
import com.book.library.repository.FamilyRepository;
import com.book.library.service.ConverterMapper;
import com.book.library.service.FamilyService;

@Service
public class FamilyServiceImpl implements FamilyService, ConverterMapper<FamilyDTO, Family> {

	private static final Logger LOG = LoggerFactory.getLogger(FamilyServiceImpl.class);

	@Autowired
	private FamilyRepository familyRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<FamilyDTO> getAllFamilies() {
		LOG.info("getAllFamilies method service.");
		Iterable<Family> familyIterable = familyRepository.findAll();
		
		List<Family> familyList = new ArrayList<>();
		familyIterable.forEach(familyList::add);
		
		List<FamilyDTO> familyDTOList = familyList.stream().map(book->convertToDto(book)).collect(Collectors.toList());
		return familyDTOList;
	}
	
	public FamilyDTO getFamilyById(Integer id) {
		Optional<Family> optionalFamily = familyRepository.findById(id);
		if(!optionalFamily.isPresent()) {
			return null;
		}
		Family family = optionalFamily.get();
		return this.convertToDto(family);
	}

	@Override
	public FamilyDTO insertOrUpdateFamily(FamilyDTO familyDTO) {
		LOG.info("insertOrUpdateFamily method service.");
		if(familyDTO.getId() == 0) {
			LOG.info("Creating new family...");
		}else {
			LOG.info("Updating a family. ID: {}", familyDTO.getId());
		}
		Family family = convertToEntity(familyDTO);
		family = familyRepository.save(family);
		
		return this.convertToDto(family);
	}

	@Override
	public FamilyDTO convertToDto(Family entity) {
		FamilyDTO dto = modelMapper.map(entity, FamilyDTO.class);
		return dto;
	}

	@Override
	public Family convertToEntity(FamilyDTO dto) {
		Family entity = modelMapper.map(dto, Family.class);
		return entity;
	}

}
