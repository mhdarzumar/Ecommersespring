package com.alibou.Product.models;

import java.math.BigDecimal;

public record ProductResponce(
        Integer id,
        String name,
        String Description,
        BigDecimal price,
        Double quantity,
        Integer category_id,
        String Category_name,
        String Category_description
) {
}
