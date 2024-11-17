package com.alibou.Payment.Payment;


import com.alibou.Payment.Notification.NotificationProducer;
import com.alibou.Payment.Notification.PaymentConfirmation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {


    private final PaymentRepository repo;
    private final PaymentMapper mapper;
    private final NotificationProducer notificationProducer;

    public Integer CreatePayment(PaymentRequest request) {

        var payment = this.repo.save(mapper.toPayment(request));

        this.notificationProducer.sendNotification(
                new PaymentConfirmation(

                request.orderRef(),
                request.totalAmount(),
                request.paymentMethod(),
                request.customer().firstName(),
                request.customer().lastName(),
                request.customer().email()
        ));

        return request.id();
    }
}
