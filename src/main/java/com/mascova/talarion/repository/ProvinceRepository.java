package com.mascova.talarion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.mascova.talarion.domain.Province;

/**
 * Spring Data JPA repository for the Author entity.
 */
public interface ProvinceRepository extends JpaRepository<Province, Long>,
    JpaSpecificationExecutor<Province> {

}
