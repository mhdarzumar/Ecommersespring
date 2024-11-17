package com.alibou.Payment.Payment;


import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;


public record PaymentRequest(
        Integer id,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderRef,
        Customer customer
) {
}
