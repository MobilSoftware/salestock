package com.mascova.talarion.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;
import com.mascova.talarion.domain.Product;
import com.mascova.talarion.domain.SalesHead;
import com.mascova.talarion.domain.SalesItem;
import com.mascova.talarion.domain.Searchable;
import com.mascova.talarion.domain.Service;
import com.mascova.talarion.repository.ProductRepository;
import com.mascova.talarion.repository.SalesHeadRepository;
import com.mascova.talarion.repository.ServiceRepository;
import com.mascova.talarion.repository.specification.SalesHeadSpecificationBuilder;
import com.mascova.talarion.service.SalesService;
import com.mascova.talarion.web.rest.form.SalesForm;
import com.mascova.talarion.web.rest.util.PaginationUtil;

/**
 * REST controller for managing SalesHead.
 */
@RestController
@RequestMapping("/api")
public class SalesHeadResource {

  private final Logger log = LoggerFactory.getLogger(SalesHeadResource.class);

  @Inject
  private SalesHeadRepository SalesHeadRepository;

  @Inject
  private SalesService salesService;

  @Inject
  private ProductRepository productRepository;

  @Inject
  private ServiceRepository serviceRepository;

  /**
   * POST /salesHead -> Create a new SalesHead.
   */
  @RequestMapping(value = "/salesHead", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
  @Timed
  public ResponseEntity<Void> create(@RequestBody SalesForm salesForm) throws URISyntaxException {

    SalesHead salesHead = new SalesHead();
    BeanUtils.copyProperties(salesForm, salesHead);
    salesHead.setSalesItems(new HashSet<SalesItem>(Arrays.asList(salesForm.getSalesItemsArr())));

    log.debug("REST request to save SalesHead : {}", salesHead);
    if (salesHead.getId() != null) {
      return ResponseEntity.badRequest()
          .header("Failure", "A new SalesHead cannot already have an ID").build();
    }
    salesService.createSales(salesHead);
    return ResponseEntity.created(new URI("/api/salesHead/" + salesHead.getId())).build();
  }

  /**
   * PUT /salesHead -> Updates an existing SalesHead.
   */
  @RequestMapping(value = "/salesHead", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
  @Timed
  public ResponseEntity<Void> update(@RequestBody SalesForm salesForm) throws URISyntaxException {

    SalesHead salesHead = new SalesHead();
    BeanUtils.copyProperties(salesForm, salesHead);

    log.debug("REST request to update SalesHead : {}", salesHead);
    if (salesForm.getId() == null) {
      return create(salesForm);
    }
    SalesHeadRepository.save(salesHead);
    return ResponseEntity.ok().build();
  }

  /**
   * GET /salesHead -> get all the SalesHeads.
   */
  @RequestMapping(value = "/salesHead", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  @Timed
  public ResponseEntity<List<SalesHead>> getAll(
      @RequestParam(value = "page", required = false) Integer offset,
      @RequestParam(value = "size", required = false) Integer size,
      @RequestParam(value = "name", required = false) String name,
      @RequestParam(value = "name2", required = false) String name2) throws URISyntaxException {

    SalesHeadSpecificationBuilder builder = new SalesHeadSpecificationBuilder();

    if (StringUtils.isNotBlank(name)) {
      builder.with("name", ":", name);
    }

    Page<SalesHead> page = SalesHeadRepository.findAll(builder.build(),
        PaginationUtil.generatePageRequest(offset, size));

    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/salesHead");
    return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
  }

  /**
   * GET /salesHead/:id -> get the "id" SalesHead.
   */
  @RequestMapping(value = "/salesHead/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  @Timed
  public ResponseEntity<SalesHead> get(@PathVariable Long id) {
    log.debug("REST request to get SalesHead : {}", id);
    return Optional.ofNullable(SalesHeadRepository.findOne(id))
        .map(SalesHead -> new ResponseEntity<>(SalesHead, HttpStatus.OK))
        .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  /**
   * DELETE /salesHead/:id -> delete the "id" SalesHead.
   */
  @RequestMapping(value = "/salesHead/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
  @Timed
  public void delete(@PathVariable Long id) {
    log.debug("REST request to delete SalesHead : {}", id);
    SalesHeadRepository.delete(id);
  }

  /**
   * GET /category -> get all the categorys.
   */
  @RequestMapping(value = "/searchable", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  @Timed
  public ResponseEntity<List<Searchable>> getAll(
      @RequestParam(value = "code", required = false) String code,
      @RequestParam(value = "name", required = false) String name) throws URISyntaxException {

    List<Product> productList;
    List<Service> serviceList;
    List<Searchable> searchableList = new ArrayList<>();
    if (StringUtils.isNotBlank(code)) {
      productList = productRepository.findByCodeStartingWith(code);
      serviceList = serviceRepository.findByCodeStartingWith(code);
      searchableList.addAll(productList);
      searchableList.addAll(serviceList);
    }

    if (StringUtils.isNotBlank(name)) {
      productList = productRepository.findByNameStartingWith(name);
      serviceList = serviceRepository.findByNameStartingWith(name);
      searchableList.addAll(productList);
      searchableList.addAll(serviceList);
    }

    // CategorySpecificationBuilder builder = new CategorySpecificationBuilder();
    //
    // if (StringUtils.isNotBlank(code)) {
    // builder.with("name", ":", name);
    // }

    // Page<Category> page = categoryRepository.findAll(builder.build(),
    // PaginationUtil.generatePageRequest(offset, size));

    HttpHeaders headers = new HttpHeaders();
    return new ResponseEntity<>(searchableList, headers, HttpStatus.OK);
  }
}
