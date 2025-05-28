package com.manpower.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CompanyDTO {
    private Integer id;
    private String name;
    private String address;
    private Integer maxAssetCount;
    private String headerImageUrl;
    private String footerImageUrl;
    private String logoImageUrl; // fix me
    private String bankAccountTitle;
    private String bankAccountNumber;
    private String bankIban;
    private String bankName;
    private String VAT;
}
