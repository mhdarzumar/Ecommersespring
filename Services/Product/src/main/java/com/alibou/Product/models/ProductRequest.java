package com.alibou.Product.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProductRequest(


         Integer id,

         @NotNull(message = "name is required")
         String name,

         @NotNull(message = "description is required")
         String description,

         @NotNull(message = "quantity is required")
         Double availableQuantity,

         @NotNull(message = "price is required")
         BigDecimal price,

         @NotNull(message = "categoryid is required")
         Integer category_id

) {
}
