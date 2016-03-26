package com.mascova.talarion.web.rest.form;

import com.mascova.talarion.domain.SalesHead;
import com.mascova.talarion.domain.SalesItem;

public class SalesForm extends SalesHead {

  private SalesItem[] salesItemsArr;

  public SalesItem[] getSalesItemsArr() {
    return salesItemsArr;
  }

  public void setSalesItemsArr(SalesItem[] salesItemsArr) {
    this.salesItemsArr = salesItemsArr;
  }

}
