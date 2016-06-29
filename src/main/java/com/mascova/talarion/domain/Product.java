package com.mascova.talarion.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Product implements Serializable, Searchable {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Version
  private Long version;

  private String code;

  private String name;

  private Integer amount;

  @Column(name = "buy_price")
  private BigDecimal buyPrice;

  @Column(name = "sell_price")
  private BigDecimal sellPrice;

  @ManyToOne
  @JoinColumn(name = "category_id")
  private Category category;

  @OneToMany(mappedBy = "product")
  @JsonIgnore
  private Set<SalesItem> salesItems = new HashSet<>();

  @ManyToOne
  @JoinColumn(name = "product_size_id")
  private ProductSize productSize;

  @ManyToOne
  @JoinColumn(name = "product_color_id")
  private ProductColor productColor;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getVersion() {
    return version;
  }

  public void setVersion(Long version) {
    this.version = version;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.mascova.talarion2.domain.Searchable#getCode()
   */
  @Override
  public String getCode() {
    return code;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.mascova.talarion2.domain.Searchable#setCode(java.lang.String)
   */
  @Override
  public void setCode(String code) {
    this.code = code;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.mascova.talarion2.domain.Searchable#getName()
   */
  @Override
  public String getName() {
    return name;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.mascova.talarion2.domain.Searchable#setName(java.lang.String)
   */
  @Override
  public void setName(String name) {
    this.name = name;
  }

  public Integer getAmount() {
    return amount;
  }

  public void setAmount(Integer amount) {
    this.amount = amount;
  }

  public BigDecimal getBuyPrice() {
    return buyPrice;
  }

  public void setBuyPrice(BigDecimal buyPrice) {
    this.buyPrice = buyPrice;
  }

  public BigDecimal getSellPrice() {
    return sellPrice;
  }

  public void setSellPrice(BigDecimal sellPrice) {
    this.sellPrice = sellPrice;
  }

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }

  public Set<SalesItem> getSalesItems() {
    return salesItems;
  }

  public void setSalesItems(Set<SalesItem> salesItems) {
    this.salesItems = salesItems;
  }

  public ProductSize getProductSize() {
    return productSize;
  }

  public void setProductSize(ProductSize productSize) {
    this.productSize = productSize;
  }

  public ProductColor getProductColor() {
    return productColor;
  }

  public void setProductColor(ProductColor productColor) {
    this.productColor = productColor;
  }

}
