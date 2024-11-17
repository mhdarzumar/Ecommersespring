package com.alibou.Product.models;

import org.springframework.web.util.DefaultUriBuilderFactory;

import java.math.BigDecimal;

public record ProductPurchaseResponce(
        Integer productId,
        String name,
        String description,
        BigDecimal price,
        Double quantity
) {
}
