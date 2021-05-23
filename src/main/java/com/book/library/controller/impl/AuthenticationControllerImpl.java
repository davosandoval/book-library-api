package com.book.library.controller.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.book.library.controller.AuthenticationController;
import com.book.library.dto.LoginDTO;

@RestController
public class AuthenticationControllerImpl implements AuthenticationController{
	private static final Logger LOG = LoggerFactory.getLogger(AuthenticationControllerImpl.class);
	
	
	public void fakeLogin(@RequestBody LoginDTO login) {
		LOG.error("Add Spring Security Manager authentication...");
        throw new IllegalStateException("Add Spring Security to manage authentication...");
    }
	

}
