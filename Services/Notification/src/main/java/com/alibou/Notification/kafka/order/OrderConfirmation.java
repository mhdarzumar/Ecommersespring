package com.alibou.Notification.kafka.order;

import com.alibou.Notification.kafka.payment.PaymentMethod;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(

        String OrderRef,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        List<PurchaseResponce> purchaseResponse,
        Customer customer
) {
}
