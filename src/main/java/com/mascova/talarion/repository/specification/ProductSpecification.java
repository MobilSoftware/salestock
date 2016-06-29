package com.mascova.talarion.repository.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.mascova.talarion.domain.Category;
import com.mascova.talarion.domain.Product;
import com.mascova.talarion.domain.ProductColor;
import com.mascova.talarion.domain.ProductSize;

public class ProductSpecification implements Specification<Product> {

  private SearchCriteria criteria;

  public ProductSpecification(SearchCriteria criteria) {
    super();
    this.criteria = criteria;
  }

  @Override
  public Predicate toPredicate(Root<Product> productRoot, CriteriaQuery<?> query,
      CriteriaBuilder builder) {
    if (criteria.getOperation().equalsIgnoreCase(">")) {
      return builder.greaterThanOrEqualTo(productRoot.<String> get(criteria.getKey()), criteria
          .getValue().toString());
    } else if (criteria.getOperation().equalsIgnoreCase("<")) {
      return builder.lessThanOrEqualTo(productRoot.<String> get(criteria.getKey()), criteria
          .getValue().toString());
    } else if (criteria.getOperation().equalsIgnoreCase(":")) {
      if (criteria.getKey().equalsIgnoreCase("category.name")) {
        Join<Product, Category> categoryJoin = productRoot.join("category");
        return builder.like(categoryJoin.<String> get("name"), "%" + criteria.getValue() + "%");
      } else if (criteria.getKey().equalsIgnoreCase("productSize.name")) {
        Join<Product, ProductSize> productSizeJoin = productRoot.join("productSize");
        return builder.like(productSizeJoin.<String> get("name"), "%" + criteria.getValue() + "%");
      } else if (criteria.getKey().equalsIgnoreCase("productColor.name")) {
        Join<Product, ProductColor> productColorJoin = productRoot.join("productColor");
        return builder.like(productColorJoin.<String> get("name"), "%" + criteria.getValue() + "%");
      } else if (productRoot.get(criteria.getKey()).getJavaType() == String.class) {
        return builder.like(productRoot.<String> get(criteria.getKey()), "%" + criteria.getValue()
            + "%");
      } else {

        return builder.equal(productRoot.get(criteria.getKey()), criteria.getValue());
      }
    }
    return null;
  }
}
