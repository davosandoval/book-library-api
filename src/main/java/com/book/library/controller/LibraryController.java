package com.book.library.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.book.library.dto.BookDTO;
import com.book.library.dto.FamilyDTO;
import com.book.library.util.Formats;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "LibraryController", description = "Manages all book operations")
@Validated
@RequestMapping("api/v1/library/")
public interface LibraryController {

	@ApiOperation(value = "Returns a single book.", notes = "Use TXT or XLS as format parameter to download it as a file. Leave it empty to return it as JSON object", response = BookDTO.class)
	@RequestMapping(value = "books/{id}", method = RequestMethod.GET, produces = {
			"application/json" })
	@PreAuthorize("hasAnyRole('ROLE_OWNER','ROLE_VIEWER')")
	public ResponseEntity<BookDTO> viewBook(HttpServletResponse response,
			@RequestParam(name = "format", required = false) Formats format, @PathVariable("id") Integer id);

	@ApiOperation(value = "Returns a single book.", notes = "Use TXT or XLS as format parameter to download it as a file. Leave it empty to return it as JSON object")
	@RequestMapping(value = "books/{id}", method = RequestMethod.GET, produces = {
			"application/octet-stream"})
	@PreAuthorize("hasAnyRole('ROLE_OWNER','ROLE_VIEWER')")
	public void downloadBookAsFile(HttpServletResponse response,
			@RequestParam(name = "format", required = false) Formats format, @PathVariable("id") Integer id) throws IOException;

	@ApiOperation(value = "Gets all the books as a collection", notes = "Use TXT or XLS as format download to export it as a file. Leave it empty to return it as JSON object", response = BookDTO.class)
	@RequestMapping(value = "books", method = RequestMethod.GET, produces = { "application/json" })
	@PreAuthorize("hasAnyRole('ROLE_OWNER','ROLE_VIEWER')")
	public ResponseEntity<List<BookDTO>> viewAllBooks(HttpServletResponse response,
			@RequestParam(name = "format", required = false) Formats format);
	
	@ApiOperation(value = "Gets all the books as a collection", notes = "Use TXT or XLS as format parameter to download it as a file. Leave it empty to return it as JSON object")
	@RequestMapping(value = "books", method = RequestMethod.GET, produces = { "application/octet-stream" })
	@PreAuthorize("hasAnyRole('ROLE_OWNER','ROLE_VIEWER')")
	public void downloadAllBooksAsFile(HttpServletResponse response,
			@RequestParam(name = "format", required = false) Formats format) throws IOException;

	
	
	@ApiOperation(value = "Returns a book collection by family id", notes = "Use TXT or XLS as format parameter to download it as a file. Leave it empty to return it as JSON object", response = BookDTO.class)
	@RequestMapping(value = "families/{familyId}/books", method = RequestMethod.GET, produces = { "application/json" })
	@PreAuthorize("hasAnyRole('ROLE_OWNER','ROLE_VIEWER')")
	public ResponseEntity<List<BookDTO>> viewBooksByFamily(HttpServletResponse response,
			@RequestParam(name = "format", required = false) Formats format, @PathVariable("familyId") Integer familyId);
	
	@ApiOperation(value = "Returns a book collection by a family id", notes = "Use TXT or XLS as format parameter to download it as a file. Leave it empty to return it as JSON object")
	@RequestMapping(value = "families/{familyId}/books", method = RequestMethod.GET, produces = { "application/octet-stream" })
	@PreAuthorize("hasAnyRole('ROLE_OWNER','ROLE_VIEWER')")
	public void downloadBooksByFamilyAsFile(HttpServletResponse response,
			@RequestParam(name = "format", required = false) Formats format, @PathVariable("familyId") Integer familyId) throws IOException;
	

	@ApiOperation(value = "Creates a new book", notes = "Creates a new book", response = BookDTO.class)
	@RequestMapping(value = "families/{familyId}/books", method = RequestMethod.POST, produces = { "application/json" })
	@PreAuthorize("hasRole('ROLE_OWNER')")
	public ResponseEntity<BookDTO> registerNewBook(@PathVariable("familyId") Integer familyId,
			@RequestBody @Valid BookDTO bookDTO);

	@ApiOperation(value = "Updates an existing book", notes = "Updates an existing book", response = BookDTO.class)
	@RequestMapping(value = "families/{familyId}/books/{id}", method = RequestMethod.PUT, produces = {
			"application/json" })
	@PreAuthorize("hasRole('ROLE_OWNER')")
	public ResponseEntity<BookDTO> updateBook(@PathVariable("familyId") Integer familyId,
			@PathVariable("id") Integer id, @RequestBody @Valid BookDTO bookDTO);

	@ApiOperation(value = "Returns the book families collection", notes = "View all book families collection", response = List.class)
	@RequestMapping(value = "families", method = RequestMethod.GET, produces = { "application/json" })
	@PreAuthorize("hasAnyRole('ROLE_OWNER','ROLE_VIEWER')")
	public ResponseEntity<List<FamilyDTO>> viewAllFamilies();
	
	@ApiOperation(value = "Creates a new book family", notes = "Creates a new book family", response = FamilyDTO.class)
	@RequestMapping(value = "families", method = RequestMethod.POST, produces = { "application/json" })
	@PreAuthorize("hasRole('ROLE_OWNER')")
	public ResponseEntity<FamilyDTO> registerNewFamily(@RequestBody @Valid FamilyDTO familyDTO);

	@ApiOperation(value = "Updates an exisitng book family", notes = "Updates an existing book family", response = FamilyDTO.class)
	@RequestMapping(value = "families/{id}", method = RequestMethod.PUT, produces = { "application/json" })
	@PreAuthorize("hasRole('ROLE_OWNER')")
	public ResponseEntity<FamilyDTO> updateFamily(@PathVariable("id") Integer id,
			@RequestBody @Valid FamilyDTO familyDTO);
}
