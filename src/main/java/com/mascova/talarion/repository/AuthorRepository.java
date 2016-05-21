package com.mascova.talarion.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mascova.talarion.domain.Author;

/**
 * Spring Data JPA repository for the Author entity.
 */
@SuppressWarnings("unused")
public interface AuthorRepository extends JpaRepository<Author, Long> {

}
