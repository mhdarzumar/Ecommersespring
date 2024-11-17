package com.alibou.Product.models;

import jakarta.validation.constraints.NotNull;

public record ProductPurchaseRequest(

        @NotNull(message ="product id is required")
        Integer id,

        @NotNull(message = "quantity is required")
        Double Quantity
) {
}
