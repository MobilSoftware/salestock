package com.mascova.talarion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.mascova.talarion.domain.Service;

/**
 * Spring Data JPA repository for the Product entity.
 */
public interface ServiceRepository extends JpaRepository<Service, Long>,
    JpaSpecificationExecutor<Service> {

  List<Service> findByCodeStartingWith(String code);

  List<Service> findByNameStartingWith(String name);

}
