package com.mascova.talarion.repository.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.mascova.talarion.domain.ProductSize;

public class ProductSizeSpecification implements Specification<ProductSize> {

  private SearchCriteria criteria;

  public ProductSizeSpecification(SearchCriteria criteria) {
    super();
    this.criteria = criteria;
  }

  @Override
  public Predicate toPredicate(Root<ProductSize> productSizeRoot, CriteriaQuery<?> query,
      CriteriaBuilder builder) {
    if (criteria.getOperation().equalsIgnoreCase(">")) {
      return builder.greaterThanOrEqualTo(productSizeRoot.<String> get(criteria.getKey()), criteria
          .getValue().toString());
    } else if (criteria.getOperation().equalsIgnoreCase("<")) {
      return builder.lessThanOrEqualTo(productSizeRoot.<String> get(criteria.getKey()), criteria
          .getValue().toString());
    } else if (criteria.getOperation().equalsIgnoreCase(":")) {
      if (productSizeRoot.get(criteria.getKey()).getJavaType() == String.class) {
        return builder.like(productSizeRoot.<String> get(criteria.getKey()),
            "%" + criteria.getValue() + "%");
      } else {

        return builder.equal(productSizeRoot.get(criteria.getKey()), criteria.getValue());
      }
    }
    return null;
  }
}
