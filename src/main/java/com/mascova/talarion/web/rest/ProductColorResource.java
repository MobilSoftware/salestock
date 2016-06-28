package com.mascova.talarion.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.mascova.talarion.domain.ProductColor;
import com.mascova.talarion.repository.ProductColorRepository;
import com.mascova.talarion.repository.specification.ProductColorSpecificationBuilder;
import com.mascova.talarion.web.rest.util.PaginationUtil;

/**
 * REST controller for managing ProductColor.
 */
@RestController
@RequestMapping("/api")
public class ProductColorResource {

  private final Logger log = LoggerFactory.getLogger(ProductColorResource.class);

  @Inject
  private ProductColorRepository productColorRepository;

  /**
   * POST /productColor -> Create a new productColor.
   */
  @RequestMapping(value = "/productColor", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
  @Timed
  public ResponseEntity<Void> create(@RequestBody ProductColor productColor)
      throws URISyntaxException {
    log.debug("REST request to save ProductColor : {}", productColor);
    if (productColor.getId() != null) {
      return ResponseEntity.badRequest()
          .header("Failure", "A new productColor cannot already have an ID").build();
    }
    productColorRepository.save(productColor);
    return ResponseEntity.created(new URI("/api/productColor/" + productColor.getId())).build();
  }

  /**
   * PUT /productColor -> Updates an existing productColor.
   */
  @RequestMapping(value = "/productColor", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
  @Timed
  public ResponseEntity<Void> update(@RequestBody ProductColor productColor)
      throws URISyntaxException {
    log.debug("REST request to update ProductColor : {}", productColor);
    if (productColor.getId() == null) {
      return create(productColor);
    }
    productColorRepository.save(productColor);
    return ResponseEntity.ok().build();
  }

  /**
   * GET /productColor -> get all the productColors.
   */
  @RequestMapping(value = "/productColor", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  @Timed
  public ResponseEntity<List<ProductColor>> getAll(
      @RequestParam(value = "page", required = false) Integer offset,
      @RequestParam(value = "size", required = false) Integer size,
      @RequestParam(value = "name", required = false) String name,
      @RequestParam(value = "name2", required = false) String name2) throws URISyntaxException {

    ProductColorSpecificationBuilder builder = new ProductColorSpecificationBuilder();

    if (StringUtils.isNotBlank(name)) {
      builder.with("name", ":", name);
    }

    Page<ProductColor> page = productColorRepository.findAll(builder.build(),
        PaginationUtil.generatePageRequest(offset, size));

    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/productColor");
    return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
  }

  /**
   * GET /productColor/:id -> get the "id" productColor.
   */
  @RequestMapping(value = "/productColor/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  @Timed
  public ResponseEntity<ProductColor> get(@PathVariable Long id) {
    log.debug("REST request to get ProductColor : {}", id);
    return Optional.ofNullable(productColorRepository.findOne(id))
        .map(productColor -> new ResponseEntity<>(productColor, HttpStatus.OK))
        .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  /**
   * DELETE /productColor/:id -> delete the "id" productColor.
   */
  @RequestMapping(value = "/productColor/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
  @Timed
  public void delete(@PathVariable Long id) {
    log.debug("REST request to delete ProductColor : {}", id);
    productColorRepository.delete(id);
  }
}
