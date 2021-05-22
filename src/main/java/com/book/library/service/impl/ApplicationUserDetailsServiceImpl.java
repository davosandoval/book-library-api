package com.book.library.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.book.library.auth.ToDoUserDetails;
import com.book.library.repository.LibraryUserDetailsRepository;

@Service
public class ApplicationUserDetailsServiceImpl implements UserDetailsService {

	private static final Logger LOG = LoggerFactory.getLogger(ApplicationUserDetailsServiceImpl.class);
	@Autowired
	private LibraryUserDetailsRepository libraryUserDetailsRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		LOG.info("Finding userName: {} ...", username);
		ToDoUserDetails toDoUserDetails = libraryUserDetailsRepository.findByUsername(username);
		
		if(toDoUserDetails == null) {
			LOG.error("UserName not found {} ...", username);
			throw new UsernameNotFoundException(String.format("Username %s not found", username));
		}
		toDoUserDetails.setAuthorities(toDoUserDetails.getUserRole().getGrantedAuthorities());
		return toDoUserDetails;
		
	}

}
