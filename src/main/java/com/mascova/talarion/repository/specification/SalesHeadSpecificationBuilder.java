package com.mascova.talarion.repository.specification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import com.mascova.talarion.domain.SalesHead;

public class SalesHeadSpecificationBuilder {

  private final List<SearchCriteria> params;

  public SalesHeadSpecificationBuilder() {
    params = new ArrayList<SearchCriteria>();
  }

  public SalesHeadSpecificationBuilder with(String key, String operation, Object value) {
    params.add(new SearchCriteria(key, operation, value));
    return this;
  }

  public Specification<SalesHead> build() {
    if (params.size() == 0) {
      return null;
    }

    List<Specification<SalesHead>> specs = new ArrayList<Specification<SalesHead>>();
    for (SearchCriteria param : params) {
      specs.add(new SalesHeadSpecification(param));
    }

    Specification<SalesHead> result = specs.get(0);
    for (int i = 1; i < specs.size(); i++) {
      result = Specifications.where(result).and(specs.get(i));
    }
    return result;
  }

}
