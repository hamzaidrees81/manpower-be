package com.manpower.model.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class DetailedProjectInvoice {
  Integer projectId;
  String projectNumber;
  String projectName;

  List<DetailedAssetInvoice> assetInvoicesList;
}
