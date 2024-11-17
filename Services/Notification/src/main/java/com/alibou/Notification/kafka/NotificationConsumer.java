package com.alibou.Notification.kafka;


import com.alibou.Notification.Notification.Notification;
import com.alibou.Notification.Notification.NotificationRepository;
import com.alibou.Notification.Notification.NotificationType;
import com.alibou.Notification.email.EmailService;
import com.alibou.Notification.kafka.order.OrderConfirmation;
import com.alibou.Notification.kafka.payment.PaymentConfirmation;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static java.lang.String.format;

@RequiredArgsConstructor
@Slf4j
@Service
public class NotificationConsumer {

    private final NotificationRepository repository;
    private final EmailService service;

    @KafkaListener(topics = "payment-topic")
    public void consumerPaymentSuccessful(PaymentConfirmation paymentConfirmation) throws MessagingException {
        log.info(format("bro notification for mgs sending paymenttopic %s"),paymentConfirmation);

        repository.save(
                Notification.builder()
                        .notificationType(NotificationType.PAYMENT_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
                        .paymentConfirmation(paymentConfirmation)
                .build()
        );

        String name = paymentConfirmation.customerFirstname()+" "+paymentConfirmation.customerLastname();
        service.SendPaymentSuccessful(
                paymentConfirmation.customerEmail(),
                name,
                paymentConfirmation.orderReference(),
                paymentConfirmation.amount()
        );
    }

    @KafkaListener(topics = "order-topic")
    public void consumerOrderConfirmation(OrderConfirmation orderConfirmation) throws MessagingException {
        log.info(format("bro notification for mgs sending paymenttopic %s"),orderConfirmation);

        repository.save(
                Notification.builder()
                        .notificationType(NotificationType.ORDER_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
                        .orderConfirmation(orderConfirmation)
                        .build()
        );

        String name = orderConfirmation.customer().fname()+" "+orderConfirmation.customer().lname();
        service.SendPaymentSuccessful(
                orderConfirmation.customer().email(),
                name,
                orderConfirmation.OrderRef(),
                orderConfirmation.totalAmount()
        );
    }

}

