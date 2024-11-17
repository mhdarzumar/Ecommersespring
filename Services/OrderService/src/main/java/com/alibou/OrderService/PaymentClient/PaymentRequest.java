package com.alibou.OrderService.PaymentClient;

import com.alibou.OrderService.Customer.CustomerResponse;
import com.alibou.OrderService.OrderModel.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(

        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderRef,
        CustomerResponse customer

) {
}
