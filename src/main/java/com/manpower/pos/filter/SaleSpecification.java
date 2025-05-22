package com.manpower.pos.filter;

import com.manpower.pos.model.Sale;
import com.manpower.pos.dto.SaleFilterRequest;
import org.springframework.data.jpa.domain.Specification;

import java.time.Instant;
import java.math.BigDecimal;

public class SaleSpecification {

    public static Specification<Sale> filter(SaleFilterRequest request) {
        return Specification
                .where(hasClientId(request.getClientId()))
                .and(hasCompanyId(request.getCompanyId()))
                .and(hasShopId(request.getShopId()))
                .and(hasStatus(request.getStatus()))
                .and(saleDateBetween(request.getStartDate(), request.getEndDate()))
                .and(hasMinTotalAmount(request.getMinTotalAmount()));
    }

    private static Specification<Sale> hasClientId(Integer clientId) {
        return (root, query, cb) ->
                clientId == null ? null : cb.equal(root.get("clientId"), clientId);
    }

    private static Specification<Sale> hasCompanyId(Integer companyId) {
        return (root, query, cb) ->
                companyId == null ? null : cb.equal(root.get("company").get("id"), companyId);
    }

    private static Specification<Sale> hasShopId(Integer shopId) {
        return (root, query, cb) ->
                shopId == null ? null : cb.equal(root.get("shop").get("id"), shopId);
    }

    private static Specification<Sale> hasStatus(Enum<?> status) {
        return (root, query, cb) ->
                status == null ? null : cb.equal(root.get("status"), status);
    }

    private static Specification<Sale> saleDateBetween(Instant start, Instant end) {
        return (root, query, cb) -> {
            if (start != null && end != null) {
                return cb.between(root.get("saleDate"), start, end);
            } else if (start != null) {
                return cb.greaterThanOrEqualTo(root.get("saleDate"), start);
            } else if (end != null) {
                return cb.lessThanOrEqualTo(root.get("saleDate"), end);
            } else {
                return null;
            }
        };
    }

    private static Specification<Sale> hasMinTotalAmount(BigDecimal minAmount) {
        return (root, query, cb) ->
                minAmount == null ? null : cb.greaterThanOrEqualTo(root.get("totalAmount"), minAmount);
    }
}
