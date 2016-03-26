package com.mascova.talarion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.mascova.talarion.domain.SubDistrict;

/**
 * Spring Data JPA repository for the Author entity.
 */
public interface SubDistrictRepository extends JpaRepository<SubDistrict, Long>,
    JpaSpecificationExecutor<SubDistrict> {

}
