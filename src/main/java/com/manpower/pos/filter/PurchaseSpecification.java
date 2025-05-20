package com.manpower.pos.filter;

import com.manpower.pos.dto.PurchaseFilterDTO;
import com.manpower.pos.model.Purchase;
import org.springframework.data.jpa.domain.Specification;

public class PurchaseSpecification {

    public static Specification<Purchase> filter(PurchaseFilterDTO dto) {
        return (root, query, cb) -> {
            var predicates = cb.conjunction();

            if (dto.getPurchaseId() != null) {
                predicates.getExpressions().add(cb.equal(root.get("id"), dto.getPurchaseId()));
            }

            if (dto.getSupplierId() != null) {
                predicates.getExpressions().add(cb.equal(root.get("supplier").get("id"), dto.getSupplierId()));
            }

            if (dto.getShopId() != null) {
                predicates.getExpressions().add(cb.equal(root.get("shop").get("id"), dto.getShopId()));
            }

            if (dto.getSupplierInvoiceNumber() != null && !dto.getSupplierInvoiceNumber().trim().isEmpty()) {
                String invoice = dto.getSupplierInvoiceNumber().trim().toLowerCase();
                predicates.getExpressions().add(
                        cb.like(cb.lower(cb.trim(root.get("supplierInvoiceNumber"))), "%" + invoice + "%")
                );
            }

            if (dto.getDateFrom() != null) {
                predicates.getExpressions().add(cb.greaterThanOrEqualTo(root.get("date"), dto.getDateFrom()));
            }

            if (dto.getDateTo() != null) {
                predicates.getExpressions().add(cb.lessThanOrEqualTo(root.get("date"), dto.getDateTo()));
            }

            if (dto.getStatus() != null) {
                predicates.getExpressions().add(cb.equal(root.get("status"), dto.getStatus()));
            }

            return predicates;
        };
    }
}
