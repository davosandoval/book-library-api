package com.book.library.repository;

import org.springframework.data.repository.CrudRepository;

import com.book.library.entity.Family;

public interface FamilyRepository extends CrudRepository<Family, Integer> {

}
