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
import com.mascova.talarion.domain.Service;
import com.mascova.talarion.repository.ServiceRepository;
import com.mascova.talarion.repository.specification.ServiceSpecificationBuilder;
import com.mascova.talarion.web.rest.util.PaginationUtil;

/**
 * REST controller for managing Service.
 */
@RestController
@RequestMapping("/api")
public class ServiceResource {

  private final Logger log = LoggerFactory.getLogger(ServiceResource.class);

  @Inject
  private ServiceRepository serviceRepository;

  /**
   * POST /service -> Create a new service.
   */
  @RequestMapping(value = "/service", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
  @Timed
  public ResponseEntity<Void> create(@RequestBody Service service) throws URISyntaxException {
    log.debug("REST request to save Service : {}", service);
    if (service.getId() != null) {
      return ResponseEntity.badRequest()
          .header("Failure", "A new service cannot already have an ID").build();
    }
    serviceRepository.save(service);
    return ResponseEntity.created(new URI("/api/service/" + service.getId())).build();
  }

  /**
   * PUT /service -> Updates an existing service.
   */
  @RequestMapping(value = "/service", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
  @Timed
  public ResponseEntity<Void> update(@RequestBody Service service) throws URISyntaxException {
    log.debug("REST request to update Service : {}", service);
    if (service.getId() == null) {
      return create(service);
    }
    serviceRepository.save(service);
    return ResponseEntity.ok().build();
  }

  /**
   * GET /service -> get all the services.
   */
  @RequestMapping(value = "/service", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  @Timed
  public ResponseEntity<List<Service>> getAll(
      @RequestParam(value = "page", required = false) Integer offset,
      @RequestParam(value = "size", required = false) Integer size,
      @RequestParam(value = "name", required = false) String name,
      @RequestParam(value = "categoryName", required = false) String categoryName)
      throws URISyntaxException {

    ServiceSpecificationBuilder builder = new ServiceSpecificationBuilder();

    if (StringUtils.isNotBlank(name)) {
      builder.with("name", ":", name);
    }
    if (StringUtils.isNotBlank(categoryName)) {
      builder.with("category.name", ":", categoryName);
    }

    Page<Service> page = serviceRepository.findAll(builder.build(),
        PaginationUtil.generatePageRequest(offset, size));
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/service");
    return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
  }

  /**
   * GET /service/:id -> get the "id" service.
   */
  @RequestMapping(value = "/service/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  @Timed
  public ResponseEntity<Service> get(@PathVariable Long id) {
    log.debug("REST request to get Service : {}", id);
    return Optional.ofNullable(serviceRepository.findOne(id))
        .map(service -> new ResponseEntity<>(service, HttpStatus.OK))
        .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  /**
   * DELETE /service/:id -> delete the "id" service.
   */
  @RequestMapping(value = "/service/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
  @Timed
  public void delete(@PathVariable Long id) {
    log.debug("REST request to delete Service : {}", id);
    serviceRepository.delete(id);
  }
}
