package com.manpower.repository;

import com.manpower.common.PaymentConstant;
import com.manpower.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    List<Payment> findAllByMainAccountId(Integer mainAccountId);

    List<Payment> findAllByPaidToTypeAndPaidToId(String paidToType, Integer paidToId);

    List<Payment> findAllByInvoiceId(Integer invoiceId);


    @Query("SELECT p FROM Payment p WHERE " +
            "(COALESCE(:mainAccountId, null) IS NULL OR p.mainAccount.id = :mainAccountId) " +
            "AND (COALESCE(:minAmount, null) IS NULL OR p.amount >= :minAmount) " +
            "AND (COALESCE(:maxAmount, null) IS NULL OR p.amount <= :maxAmount) " +
            "AND (COALESCE(:paymentMethod, null) IS NULL OR p.paymentMethod = :paymentMethod) " +
            "AND (COALESCE(:paidToType, null) IS NULL OR p.paidToType = :paidToType) " +
            "AND (COALESCE(:paidToId, null) IS NULL OR p.paidToId = :paidToId) " +
            "AND (COALESCE(:invoiceId, null) IS NULL OR p.invoiceId = :invoiceId) " +
            "AND (COALESCE(:status, null) IS NULL OR p.status = :status) " +
            "AND (COALESCE(:paymentType, null) IS NULL OR p.paymentType = :paymentType) " +
            "AND (COALESCE(:remarks, null) IS NULL OR p.remarks LIKE %:remarks%) " +
            "AND (COALESCE(:startDate, null) IS NULL OR p.paymentDate >= :startDate) " +
            "AND (COALESCE(:endDate, null) IS NULL OR p.paymentDate <= :endDate) " +
            "AND (COALESCE(:startTimestamp, null) IS NULL OR p.paymentTimestamp >= :startTimestamp) " +
            "AND (COALESCE(:endTimestamp, null) IS NULL OR p.paymentTimestamp <= :endTimestamp) " +
            "AND (COALESCE(:paymentDirection, null) IS NULL OR p.paymentDirection = :paymentDirection) " +
            "ORDER BY p.id desc"
    )
    List<Payment> filterPayments(
            @Param("mainAccountId") Integer mainAccountId,
            @Param("minAmount") BigDecimal minAmount,
            @Param("maxAmount") BigDecimal maxAmount,
            @Param("paymentMethod") String paymentMethod,
            @Param("reference") String reference,
            @Param("paidToType") String paidToType,
            @Param("paidToId") Integer paidToId,
            @Param("invoiceId") Integer invoiceId,
            @Param("status") String status,
            @Param("paymentType") String paymentType,
            @Param("remarks") String remarks,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param("startTimestamp") Instant startTimestamp,
            @Param("endTimestamp") Instant endTimestamp,
            @Param("paymentDirection") String paymentDirection
            );

    @Query("SELECT COALESCE(SUM(p.amount), 0) FROM Payment p WHERE p.paidToType = 'INVOICE' AND p.invoiceId IN :invoiceIds")
    BigDecimal sumPaidAmountByInvoiceIds(@Param("invoiceIds") List<Integer> invoiceIds);

}
