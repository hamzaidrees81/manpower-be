package com.manpower.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class InvoiceWithAsset {
  private Invoice invoice;
  private List<InvoiceAsset> invoiceAssetList;

}