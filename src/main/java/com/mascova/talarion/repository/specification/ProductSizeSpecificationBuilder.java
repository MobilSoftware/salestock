package com.mascova.talarion.repository.specification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import com.mascova.talarion.domain.ProductSize;

public class ProductSizeSpecificationBuilder {

  private final List<SearchCriteria> params;

  public ProductSizeSpecificationBuilder() {
    params = new ArrayList<SearchCriteria>();
  }

  public ProductSizeSpecificationBuilder with(String key, String operation, Object value) {
    params.add(new SearchCriteria(key, operation, value));
    return this;
  }

  public Specification<ProductSize> build() {
    if (params.size() == 0) {
      return null;
    }

    List<Specification<ProductSize>> specs = new ArrayList<Specification<ProductSize>>();
    for (SearchCriteria param : params) {
      specs.add(new ProductSizeSpecification(param));
    }

    Specification<ProductSize> result = specs.get(0);
    for (int i = 1; i < specs.size(); i++) {
      result = Specifications.where(result).and(specs.get(i));
    }
    return result;
  }

}
