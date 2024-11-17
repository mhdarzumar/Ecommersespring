package com.alibou.Notification.kafka.order;

public record Customer(
        String id,

        String fname,

        String lname,

        String email
) {
}
