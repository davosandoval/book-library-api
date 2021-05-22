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

import com.book.library.dto.BookDTO;
import com.book.library.entity.Book;
import com.book.library.entity.Family;
import com.book.library.exception.ResourceNotFoundException;
import com.book.library.repository.BookRepository;
import com.book.library.service.BookService;
import com.book.library.service.ConverterMapper;

@Service
public class BookServiceImpl implements BookService, ConverterMapper<BookDTO, Book> {
	private static final Logger LOG = LoggerFactory.getLogger(BookServiceImpl.class);
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
    private ModelMapper modelMapper;

	@Override
	public List<BookDTO> getAllBooks() {
		LOG.info("getAllBooks method service.");
		Iterable<Book> bookIterable = bookRepository.findAll();
		
		List<Book> bookList = new ArrayList<>();
		bookIterable.forEach(bookList::add);
		
		List<BookDTO> bookDTOList = bookList.stream().map(book->convertToDto(book)).collect(Collectors.toList());
		return bookDTOList;
	}

	@Override
	public List<BookDTO> getAllBooksByFamily(Integer familyId) {
		LOG.info("getAllBooksByFamily method service. familyId: {}", familyId);
		
		List<Book> bookList = bookRepository.findAllByFamilyId(familyId);
		
		List<BookDTO> bookDTOList = bookList.stream().map(book->convertToDto(book)).collect(Collectors.toList());
		return bookDTOList;
	}

	@Override
	public BookDTO getBookById(Integer id) {
		LOG.info("getBookById service. ID: {}", id);
		Optional<Book> optionalBook = bookRepository.findById(id);
		if(!optionalBook.isPresent()) {
			LOG.error("Book not found. ID: {}", id);
			throw new ResourceNotFoundException("Book not found. ID: "+id);
		}
		return this.convertToDto(optionalBook.get());
	}

	@Override
	public BookDTO insertOrUpdateBook(BookDTO bookDTO, Integer familyId) {
		LOG.info("insertOrUpdateBook method service.");
		if(bookDTO.getId() == 0) {
			LOG.info("Creating new book...");
		}else {
			LOG.info("Updating a book. ID: {}", bookDTO.getId());
		}
		Book book = convertToEntity(bookDTO);
		book.setFamily(new Family(familyId));
		book = bookRepository.save(book);
		
		Optional<Book> optionalBook = bookRepository.findById(book.getId());
		if(!optionalBook.isPresent()) {
			LOG.error("Unexpected error. Book not found. ID: {}", book.getId());
			throw new ResourceNotFoundException("Unexpected error. Book not found. ID: " + book.getId());
		}
		
		return this.convertToDto(optionalBook.get());
	}

	@Override
	public BookDTO convertToDto(Book entity) {
		BookDTO dto = modelMapper.map(entity, BookDTO.class);
		dto.setFamily(entity.getFamily().getGenre());
		return dto;
	}

	@Override
	public Book convertToEntity(BookDTO dto) {
		Book entity = modelMapper.map(dto, Book.class);
		return entity;
	}

}
