package com.book.library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.book.library.entity.Book;

public interface BookRepository extends CrudRepository<Book, Integer> {
	@Query("from Book b where b.family.id=:familyId")
	List<Book> findAllByFamilyId(@Param("familyId")Integer familyId);
}
