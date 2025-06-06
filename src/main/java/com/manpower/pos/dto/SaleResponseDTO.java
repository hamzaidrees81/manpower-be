package com.manpower.pos.dto;

import com.manpower.model.Client;
import com.manpower.model.Company;
import com.manpower.model.dto.ClientDTO;
import com.manpower.model.dto.CompanyDTO;
import com.manpower.model.dto.PaymentDTO;
import com.manpower.pos.enums.AliveStatus;
import com.manpower.pos.enums.PaymentMode;
import com.manpower.pos.model.Shop;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Data
public class SaleResponseDTO {
    private Integer id;
    private String invoiceNumber; //fix
    private Instant saleDate; //fix
    private String sellerName; //fix
    private BigDecimal totalAmount;
    private BigDecimal totalAmountBeforeVAT;
    private BigDecimal vatAmount;
    private AliveStatus status;
    private Integer customerId;
    private ClientDTO client;
    private List<SaleItemResponseDTO> saleItems;
    private String poNumber;
    private Integer shopId;
    private ShopDTO shop;
    private List<PaymentDTO> payments;
    private CompanyDTO company;
    private String QRCode;
    private BigDecimal bulkDiscountPercentage;
    private BigDecimal receivedAmount;
    private PaymentMode paymentMode;

}
