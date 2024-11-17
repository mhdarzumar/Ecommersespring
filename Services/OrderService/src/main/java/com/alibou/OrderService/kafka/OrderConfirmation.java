package com.alibou.OrderService.kafka;

import com.alibou.OrderService.Customer.CustomerResponse;
import com.alibou.OrderService.OrderModel.PaymentMethod;

import com.alibou.OrderService.Product.PurchaseResponce;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customerResponse,
        List<PurchaseResponce> produts
) {
}
