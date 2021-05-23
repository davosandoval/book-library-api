package com.book.library.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.book.library.dto.LoginDTO;

import io.swagger.annotations.ApiOperation;


@RequestMapping("/api/v1")
public interface AuthenticationController {
	@ApiOperation(value = "Login operation", notes = "Generates a token to be used in the other API methods")
	@RequestMapping(value = "login", method = RequestMethod.POST, produces = { "application/json" })
	public void fakeLogin(@RequestBody LoginDTO login);
	
}
