package com.alibou.OrderService.OrderModel;

import java.math.BigDecimal;

public record OrderResponce(
        Integer id,
        String reference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        String customerId
) {
}
