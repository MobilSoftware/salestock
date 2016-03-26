package com.mascova.talarion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.mascova.talarion.domain.SalesItem;

/**
 * Spring Data JPA repository for the Category entity.
 */
public interface SalesItemRepository extends JpaRepository<SalesItem, Long>,
    JpaSpecificationExecutor<SalesItem> {

}
