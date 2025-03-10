package com.manpower.dto;

import com.manpower.model.Client;
import lombok.Data;

import java.sql.Date;
import java.time.LocalDate;

@Data
public class InvoiceMetadata {
  Client client;
  LocalDate startDate;
  LocalDate endDate;
}
