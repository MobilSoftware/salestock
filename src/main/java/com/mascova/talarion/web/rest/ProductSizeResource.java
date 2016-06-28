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
import com.mascova.talarion.domain.ProductSize;
import com.mascova.talarion.repository.ProductSizeRepository;
import com.mascova.talarion.repository.specification.ProductSizeSpecificationBuilder;
import com.mascova.talarion.web.rest.util.PaginationUtil;

/**
 * REST controller for managing ProductSize.
 */
@RestController
@RequestMapping("/api")
public class ProductSizeResource {

  private final Logger log = LoggerFactory.getLogger(ProductSizeResource.class);

  @Inject
  private ProductSizeRepository productSizeRepository;

  /**
   * POST /productSize -> Create a new productSize.
   */
  @RequestMapping(value = "/productSize", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
  @Timed
  public ResponseEntity<Void> create(@RequestBody ProductSize productSize)
      throws URISyntaxException {
    log.debug("REST request to save ProductSize : {}", productSize);
    if (productSize.getId() != null) {
      return ResponseEntity.badRequest()
          .header("Failure", "A new productSize cannot already have an ID").build();
    }
    productSizeRepository.save(productSize);
    return ResponseEntity.created(new URI("/api/productSize/" + productSize.getId())).build();
  }

  /**
   * PUT /productSize -> Updates an existing productSize.
   */
  @RequestMapping(value = "/productSize", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
  @Timed
  public ResponseEntity<Void> update(@RequestBody ProductSize productSize)
      throws URISyntaxException {
    log.debug("REST request to update ProductSize : {}", productSize);
    if (productSize.getId() == null) {
      return create(productSize);
    }
    productSizeRepository.save(productSize);
    return ResponseEntity.ok().build();
  }

  /**
   * GET /productSize -> get all the productSizes.
   */
  @RequestMapping(value = "/productSize", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  @Timed
  public ResponseEntity<List<ProductSize>> getAll(
      @RequestParam(value = "page", required = false) Integer offset,
      @RequestParam(value = "size", required = false) Integer size,
      @RequestParam(value = "name", required = false) String name,
      @RequestParam(value = "name2", required = false) String name2) throws URISyntaxException {

    ProductSizeSpecificationBuilder builder = new ProductSizeSpecificationBuilder();

    if (StringUtils.isNotBlank(name)) {
      builder.with("name", ":", name);
    }

    Page<ProductSize> page = productSizeRepository.findAll(builder.build(),
        PaginationUtil.generatePageRequest(offset, size));

    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/productSize");
    return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
  }

  /**
   * GET /productSize/:id -> get the "id" productSize.
   */
  @RequestMapping(value = "/productSize/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  @Timed
  public ResponseEntity<ProductSize> get(@PathVariable Long id) {
    log.debug("REST request to get ProductSize : {}", id);
    return Optional.ofNullable(productSizeRepository.findOne(id))
        .map(productSize -> new ResponseEntity<>(productSize, HttpStatus.OK))
        .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  /**
   * DELETE /productSize/:id -> delete the "id" productSize.
   */
  @RequestMapping(value = "/productSize/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
  @Timed
  public void delete(@PathVariable Long id) {
    log.debug("REST request to delete ProductSize : {}", id);
    productSizeRepository.delete(id);
  }
}
