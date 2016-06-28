package com.mascova.talarion.repository.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.mascova.talarion.domain.ProductColor;

public class ProductColorSpecification implements Specification<ProductColor> {

  private SearchCriteria criteria;

  public ProductColorSpecification(SearchCriteria criteria) {
    super();
    this.criteria = criteria;
  }

  @Override
  public Predicate toPredicate(Root<ProductColor> productColorRoot, CriteriaQuery<?> query,
      CriteriaBuilder builder) {
    if (criteria.getOperation().equalsIgnoreCase(">")) {
      return builder.greaterThanOrEqualTo(productColorRoot.<String> get(criteria.getKey()),
          criteria.getValue().toString());
    } else if (criteria.getOperation().equalsIgnoreCase("<")) {
      return builder.lessThanOrEqualTo(productColorRoot.<String> get(criteria.getKey()), criteria
          .getValue().toString());
    } else if (criteria.getOperation().equalsIgnoreCase(":")) {
      if (productColorRoot.get(criteria.getKey()).getJavaType() == String.class) {
        return builder.like(productColorRoot.<String> get(criteria.getKey()),
            "%" + criteria.getValue() + "%");
      } else {

        return builder.equal(productColorRoot.get(criteria.getKey()), criteria.getValue());
      }
    }
    return null;
  }
}
