package com.mascova.talarion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.mascova.talarion.domain.Village;

/**
 * Spring Data JPA repository for the Author entity.
 */
public interface VillageRepository extends JpaRepository<Village, Long>,
    JpaSpecificationExecutor<Village> {

}
