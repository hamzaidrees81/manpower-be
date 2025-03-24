package com.manpower.specification;

import com.manpower.common.Contants;
import com.manpower.model.Invoice;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class InvoiceSpecifications {

    public static Specification<Invoice> filterInvoices(Integer companyId, Integer clientId, Contants.InvoiceStatus status, LocalDate invoiceStartDate, LocalDate invoiceEndDate,LocalDate createdStartDate,LocalDate createdEndDate,LocalDate clearedStartDate,LocalDate clearedEndDate) {
        return (root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.equal(root.get("company").get("id"), companyId);

            if (clientId != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("client").get("id"), clientId));
            }
            if (status != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("status"), status.getValue()));
            }
            if (invoiceStartDate != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.greaterThanOrEqualTo(root.get("startDate"), invoiceStartDate));
            }
            if (invoiceEndDate != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.lessThanOrEqualTo(root.get("endDate"), invoiceEndDate));
            }
            if (createdStartDate != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.greaterThanOrEqualTo(root.get("create_date"), createdStartDate));
            }
            if (createdEndDate != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.lessThanOrEqualTo(root.get("create_date"), createdStartDate));
            }
            if (clearedStartDate != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.greaterThanOrEqualTo(root.get("cleared_date"), clearedStartDate));
            }
            if (clearedEndDate != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.lessThanOrEqualTo(root.get("cleared_date"), clearedEndDate));
            }

            return predicate;
        };
    }
}
