package com.mascova.talarion.service;

import java.util.Set;

import javax.inject.Inject;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.mascova.talarion.domain.SalesHead;
import com.mascova.talarion.domain.SalesItem;
import com.mascova.talarion.repository.SalesHeadRepository;
import com.mascova.talarion.repository.SalesItemRepository;

@Service
public class SalesService {

  @Inject
  private SalesHeadRepository salesHeadRepository;

  @Inject
  private SalesItemRepository salesItemRepository;

  public void createSales(SalesHead salesHead) {

    SalesHead bareSalesHead = new SalesHead();
    BeanUtils.copyProperties(salesHead, bareSalesHead, "salesItems");
    Set<SalesItem> transientSalesItems = salesHead.getSalesItems();

    bareSalesHead = salesHeadRepository.save(bareSalesHead);
    bareSalesHead.setSalesItems(salesHead.getSalesItems());

  }

}
