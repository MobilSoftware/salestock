package com.mascova.talarion.repository.specification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import com.mascova.talarion.domain.ProductColor;

public class ProductColorSpecificationBuilder {

  private final List<SearchCriteria> params;

  public ProductColorSpecificationBuilder() {
    params = new ArrayList<SearchCriteria>();
  }

  public ProductColorSpecificationBuilder with(String key, String operation, Object value) {
    params.add(new SearchCriteria(key, operation, value));
    return this;
  }

  public Specification<ProductColor> build() {
    if (params.size() == 0) {
      return null;
    }

    List<Specification<ProductColor>> specs = new ArrayList<Specification<ProductColor>>();
    for (SearchCriteria param : params) {
      specs.add(new ProductColorSpecification(param));
    }

    Specification<ProductColor> result = specs.get(0);
    for (int i = 1; i < specs.size(); i++) {
      result = Specifications.where(result).and(specs.get(i));
    }
    return result;
  }

}
