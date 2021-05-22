package com.book.library.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.book.library.auth.BannedToken;
import com.book.library.repository.BannedTokenRepository;
import com.book.library.service.BannedTokenService;

@Service
public class BannedTokenServiceImpl implements BannedTokenService {
	private static final Logger LOG = LoggerFactory.getLogger(BannedTokenServiceImpl.class);
	
	@Autowired
	BannedTokenRepository bannedTokenRepository;

	@Override
	public boolean disallowToken(String token) {
		LOG.info("Disallow token service. Token: {}", token);
		BannedToken bannedToken = new BannedToken();
		bannedToken.setToken(token);
		bannedToken = bannedTokenRepository.save(bannedToken);
		return bannedToken != null?true:false;
	}

	@Override
	public boolean verifyIfTokenExists(String token) {
		LOG.info("Verify if token exists service. Token: {}", token);
		BannedToken bannedToken = bannedTokenRepository.findOne(token);
		return bannedToken != null?true:false;
	}

	

}
