package com.mascova.talarion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.mascova.talarion.domain.ProductSize;

/**
 * Spring Data JPA repository for the Product entity.
 */
public interface ProductSizeRepository extends JpaRepository<ProductSize, Long>,
    JpaSpecificationExecutor<ProductSize> {

  List<ProductSize> findByCodeStartingWith(String code);

  List<ProductSize> findByNameStartingWith(String name);

}
