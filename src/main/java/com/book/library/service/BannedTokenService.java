package com.book.library.service;

public interface BannedTokenService {
	boolean disallowToken(String token);
	boolean verifyIfTokenExists(String token);
}
