package com.alibou.OrderService.OrderModel;

import com.alibou.OrderService.Product.PurchaseRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;

public record OrderRequest(

        Integer id,

        @NotNull(message = "ref should be present")
        String reference,

        @Positive(message = "amont should be positive")
        BigDecimal totalAmount,

        @NotNull(message = "Payment method should be precised")
        PaymentMethod paymentMethod,
        @NotNull(message = "Customer should be present")
        @NotEmpty(message = "Customer should be present")
        @NotBlank(message = "Customer should be present")
        String customerId,

        @NotEmpty(message = "list required")
        List<PurchaseRequest> purchaseList
) {
}
