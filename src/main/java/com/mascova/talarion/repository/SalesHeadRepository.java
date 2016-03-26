package com.mascova.talarion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mascova.talarion.domain.SalesHead;

/**
 * Spring Data JPA repository for the Category entity.
 */
public interface SalesHeadRepository extends JpaRepository<SalesHead, Long>,
    JpaSpecificationExecutor<SalesHead> {

  @Query("SELECT p,s FROM Product p, Service s WHERE p.code LIKE :code AND s.code LIKE :code")
  public List<Object[]> findProductAndServiceByCode(@Param("code") String code);

}
