package com.manpower.repository;

import com.manpower.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {
}
