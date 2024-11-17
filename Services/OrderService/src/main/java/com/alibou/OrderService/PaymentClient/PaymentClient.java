package com.alibou.OrderService.PaymentClient;


import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "ProductService",
        url = "${application.config.payment-url}"
)
public interface PaymentClient {

    @PostMapping
    Integer CreatePayment(@RequestBody @Valid PaymentRequest request);

}
