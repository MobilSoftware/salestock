package com.mascova.talarion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.mascova.talarion.domain.Document;

/**
 * Spring Data JPA repository for the Image entity.
 */
public interface DocumentRepository extends JpaRepository<Document, Long>,
    JpaSpecificationExecutor<Document> {

}
