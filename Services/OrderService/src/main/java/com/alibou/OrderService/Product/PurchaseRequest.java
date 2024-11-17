package com.alibou.OrderService.Product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;


public record PurchaseRequest(

        @NotNull(message = "Id of product required")
        Integer id,

        @Positive(message = "quantity must be specified")
        Double Quantity

) {

}
