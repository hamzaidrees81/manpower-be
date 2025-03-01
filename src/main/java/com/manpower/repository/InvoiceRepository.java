package com.manpower.repository;

import com.manpower.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {
  Optional<Invoice> findByClient_ClientId(String clientId);
}
