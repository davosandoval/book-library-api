package com.book.library.service;

import java.util.List;

import com.book.library.dto.BookDTO;

public interface BookService {
	List<BookDTO> getAllBooks();
	List<BookDTO> getAllBooksByFamily(Integer familyId);
	
	BookDTO getBookById(Integer id);
	BookDTO insertOrUpdateBook(BookDTO bookDTO, Integer familyId);
	
}
