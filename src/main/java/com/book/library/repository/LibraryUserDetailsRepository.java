package com.book.library.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.book.library.auth.ToDoUserDetails;

@Repository
public interface LibraryUserDetailsRepository extends CrudRepository<ToDoUserDetails, Integer> {
	@Query("from ToDoUserDetails t where t.username=:username")
	ToDoUserDetails findByUsername(@Param("username")String username);
}
