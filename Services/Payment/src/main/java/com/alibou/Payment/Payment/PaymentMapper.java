package com.alibou.Payment.Payment;


import org.springframework.stereotype.Service;

@Service
public class PaymentMapper {


    public Payment toPayment(PaymentRequest request)
    {
        return Payment.builder()
                .paymentMethod(request.paymentMethod())
                .totalAmount(request.totalAmount())
                .id(request.id())
                .orderId(request.orderId())
                .build();
    }

}
