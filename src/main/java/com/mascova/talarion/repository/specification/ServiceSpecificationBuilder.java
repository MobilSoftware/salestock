package com.mascova.talarion.repository.specification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import com.mascova.talarion.domain.Service;

public class ServiceSpecificationBuilder {

  private final List<SearchCriteria> params;

  public ServiceSpecificationBuilder() {
    params = new ArrayList<SearchCriteria>();
  }

  public ServiceSpecificationBuilder with(String key, String operation, Object value) {
    params.add(new SearchCriteria(key, operation, value));
    return this;
  }

  public Specification<Service> build() {
    if (params.size() == 0) {
      return null;
    }

    List<Specification<Service>> specs = new ArrayList<Specification<Service>>();
    for (SearchCriteria param : params) {
      specs.add(new ServiceSpecification(param));
    }

    Specification<Service> result = specs.get(0);
    for (int i = 1; i < specs.size(); i++) {
      result = Specifications.where(result).and(specs.get(i));
    }
    return result;
  }

}
