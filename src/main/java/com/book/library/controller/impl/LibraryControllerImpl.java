package com.book.library.controller.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.compress.utils.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.book.library.controller.LibraryController;
import com.book.library.dto.BookDTO;
import com.book.library.dto.FamilyDTO;
import com.book.library.exception.FileFormatNotFoundException;
import com.book.library.exception.ResourceNotFoundException;
import com.book.library.service.BookService;
import com.book.library.service.FamilyService;
import com.book.library.util.FileExporter;
import com.book.library.util.Formats;

@RestController
public class LibraryControllerImpl implements LibraryController{
	private static final Logger LOG = LoggerFactory.getLogger(LibraryControllerImpl.class);

	@Autowired
	private BookService bookService;

	@Autowired
	private FamilyService familyService;

	public ResponseEntity<BookDTO> viewBook(HttpServletResponse response,
			@RequestParam(name = "format", required = false) Formats format, @PathVariable("id") Integer id) {
		LOG.info("Processing viewBook. ID: {}", id);
		BookDTO bookDTO = bookService.getBookById(id);
		return ResponseEntity.ok().body(bookDTO);

	}

	public void downloadBookAsFile(HttpServletResponse response,
			@RequestParam(name = "format", required = false) Formats format, @PathVariable("id") Integer id) throws IOException {
		LOG.info("ViewBook as file. Format: {}, ID: {}", format, id);
		BookDTO bookDTO = bookService.getBookById(id);
		if(Formats.XLS.equals(format)) {
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment; filename=book.xlsx");
			ByteArrayInputStream stream = FileExporter.booksToExcelFile(java.util.Arrays.asList(bookDTO));
			IOUtils.copy(stream, response.getOutputStream());
		}else if(Formats.TXT.equals(format)) {
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment; filename=book.txt");
			ByteArrayInputStream stream = FileExporter.booksToTxtFile(java.util.Arrays.asList(bookDTO));
			IOUtils.copy(stream, response.getOutputStream());
		}else {
			response.setContentType("application/json");
			throw new FileFormatNotFoundException("Format not found");
		}

	}

	public ResponseEntity<List<BookDTO>> viewAllBooks(HttpServletResponse response,
			@RequestParam(name = "format", required = false) Formats format) {
		LOG.info("Processing viewAllBooks.");
		List<BookDTO> bookDTOs = bookService.getAllBooks();
		return ResponseEntity.ok().body(bookDTOs);

	}
	
	public void downloadAllBooksAsFile(HttpServletResponse response,
			@RequestParam(name = "format", required = false) Formats format) throws IOException {
		LOG.info("Processing downloadAllBooksAsFile.");
		List<BookDTO> bookDTOs = bookService.getAllBooks();
		
		if(Formats.XLS.equals(format)) {
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment; filename=books.xlsx");
			ByteArrayInputStream stream = FileExporter.booksToExcelFile(bookDTOs);
			IOUtils.copy(stream, response.getOutputStream());
		}else if(Formats.TXT.equals(format)) {
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment; filename=books.txt");
			ByteArrayInputStream stream = FileExporter.booksToTxtFile(bookDTOs);
			IOUtils.copy(stream, response.getOutputStream());
		}else {
			response.setContentType("application/json");
			throw new FileFormatNotFoundException("Format not found");
		}

	}
	
	
	public ResponseEntity<List<BookDTO>> viewBooksByFamily(HttpServletResponse response,
			@RequestParam(name = "format", required = false) Formats format, @PathVariable("familyId") Integer familyId) {
		LOG.info("Processing viewBooksByFamily. familyId: {}", familyId);
		List<BookDTO> bookDTOs = bookService.getAllBooksByFamily(familyId);
		return ResponseEntity.ok().body(bookDTOs);

	}
	
	public void downloadBooksByFamilyAsFile(HttpServletResponse response,
			@RequestParam(name = "format", required = false) Formats format, @PathVariable("familyId") Integer familyId) throws IOException {
		LOG.info("Processing downloadBooksByFamilyAsFile. familyId: {}", familyId);
		List<BookDTO> bookDTOs = bookService.getAllBooksByFamily(familyId);
		
		
		if(Formats.XLS.equals(format)) {
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment; filename=booksByFamily.xlsx");
			ByteArrayInputStream stream = FileExporter.booksToExcelFile(bookDTOs);
			IOUtils.copy(stream, response.getOutputStream());
		}else if(Formats.TXT.equals(format)) {
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment; filename=booksByFamily.txt");
			ByteArrayInputStream stream = FileExporter.booksToTxtFile(bookDTOs);
			IOUtils.copy(stream, response.getOutputStream());
		}else {
			response.setContentType("application/json");
			throw new FileFormatNotFoundException("Format not found");
		}

	}
	

	public ResponseEntity<BookDTO> registerNewBook(@PathVariable("familyId") Integer familyId,
			@RequestBody @Valid BookDTO bookDTO) {
		LOG.info("Creating a new book ...");
		if (familyService.getFamilyById(familyId) == null) {
			LOG.error("Family not found. ID: {}", familyId);
			throw new ResourceNotFoundException("Family not found. ID: " + familyId);
		}
		bookDTO.setId(0);
		bookDTO = bookService.insertOrUpdateBook(bookDTO, familyId);
		return ResponseEntity.ok().body(bookDTO);
	}

	public ResponseEntity<BookDTO> updateBook(@PathVariable("familyId") Integer familyId,
			@PathVariable("id") Integer id, @RequestBody @Valid BookDTO bookDTO) {
		LOG.info("Updating book. ID: {}", id);
		if (familyService.getFamilyById(familyId) == null) {
			LOG.error("Family not found. ID: {}", id);
			throw new ResourceNotFoundException("Family not found. ID: " + id);
		}
		bookDTO.setId(id);
		bookDTO = bookService.insertOrUpdateBook(bookDTO, familyId);
		return ResponseEntity.ok().body(bookDTO);
	}

	public ResponseEntity<List<FamilyDTO>> viewAllFamilies() {
		LOG.info("Processing viewAllFamilies...");
		List<FamilyDTO> familyDTOs = familyService.getAllFamilies();
		return ResponseEntity.ok().body(familyDTOs);
	}

	public ResponseEntity<FamilyDTO> registerNewFamily(@RequestBody @Valid FamilyDTO familyDTO) {
		LOG.info("Creating new book family...");
		familyDTO.setId(0);
		familyDTO = familyService.insertOrUpdateFamily(familyDTO);
		return ResponseEntity.ok().body(familyDTO);
	}

	public ResponseEntity<FamilyDTO> updateFamily(@PathVariable("id") Integer id,
			@RequestBody @Valid FamilyDTO familyDTO) {
		LOG.info("Updating book family. ID: {}", id);
		if (familyService.getFamilyById(id) == null) {
			LOG.error("Family not found. ID: {}", id);
			throw new ResourceNotFoundException("Family not found. ID: " + id);
		}

		familyDTO.setId(id);
		familyDTO = familyService.insertOrUpdateFamily(familyDTO);
		return ResponseEntity.ok().body(familyDTO);
	}

}
