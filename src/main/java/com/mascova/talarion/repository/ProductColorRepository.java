package com.mascova.talarion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.mascova.talarion.domain.ProductColor;

/**
 * Spring Data JPA repository for the Product entity.
 */
public interface ProductColorRepository extends JpaRepository<ProductColor, Long>,
    JpaSpecificationExecutor<ProductColor> {

  List<ProductColor> findByCodeStartingWith(String code);

  List<ProductColor> findByNameStartingWith(String name);

}
