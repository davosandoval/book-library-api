package com.book.library.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.book.library.auth.BannedToken;

@Repository
public interface BannedTokenRepository extends CrudRepository<BannedToken, Integer> {
	@Query("from BannedToken b where b.token=:token")
	BannedToken findOne(@Param("token")String token);
}
