package com.manpower.repository;

import com.manpower.model.Invoice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface InvoiceRepository extends JpaRepository<Invoice, Integer>, JpaSpecificationExecutor<Invoice> {
  Optional<List<Invoice>> findInvoicesByClient_Id(Integer clientId);

  Optional<List<Invoice>> findInvoicesByCompany_Id(Integer companyId);

  @Query("SELECT i FROM Invoice i WHERE i.company.id = :companyId " +
    "AND (:clientId IS NULL OR i.client.id = :clientId) " +
    "AND (:status IS NULL OR i.status = :status) " +
    "AND (:startDate IS NULL OR i.startDate >= :startDate) " +
    "AND (:endDate IS NULL OR i.endDate <= :endDate)")
  Page<Invoice> findFilteredInvoices(@Param("companyId") Integer companyId,
                                     @Param("clientId") Integer clientId,
                                     @Param("status") Byte status,
                                     @Param("startDate") LocalDate startDate,
                                     @Param("endDate") LocalDate endDate,
                                     Pageable pageable);
}
