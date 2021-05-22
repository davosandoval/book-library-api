package com.book.library.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.book.library.dto.LoginDTO;
import com.book.library.jwt.JwtConfig;
import com.book.library.service.BannedTokenService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v1")
public class AuthenticationController {
	private static final Logger LOG = LoggerFactory.getLogger(AuthenticationController.class);
	
	@Autowired
	private BannedTokenService bannedTokenService;
	
	@Autowired
	private JwtConfig jwtConfig;
	
	//** Operation created just for generate SWAGGER Documentation. Spring Security overrides this fakeLogin method
	@ApiOperation(value = "Login operation", notes = "Generates a token to be used in the other API methods")
	@RequestMapping(value = "login", method = RequestMethod.POST, produces = { "application/json" })
	public void fakeLogin(@RequestBody LoginDTO login) {
        throw new IllegalStateException("Add Spring Security to manage authentication...");
    }
	
	@ApiOperation(value = "Logout operation", notes = "Invalidates a given token")
	@RequestMapping(value = "logout", method = RequestMethod.GET)
	@PreAuthorize("hasAnyRole('ROLE_OWNER','ROLE_VIEWER')")
	public void logout(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		LOG.info("Processing logout for token: {}", token);
		token = token.replace(jwtConfig.getTokenPrefix(), "");
		bannedTokenService.disallowToken(token);
	}

}
