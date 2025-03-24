package com.manpower.specification;

import com.manpower.common.Contants;
import com.manpower.model.Invoice;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class InvoiceSpecifications {

    public static Specification<Invoice> filterInvoices(Integer companyId, Integer clientId, Contants.InvoiceStatus status, LocalDate startDate, LocalDate endDate) {
        return (root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.equal(root.get("company").get("id"), companyId);

            if (clientId != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("client").get("id"), clientId));
            }
            if (status != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("status"), status.getValue()));
            }
            if (startDate != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.greaterThanOrEqualTo(root.get("startDate"), startDate));
            }
            if (endDate != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.lessThanOrEqualTo(root.get("endDate"), endDate));
            }

            return predicate;
        };
    }
}
