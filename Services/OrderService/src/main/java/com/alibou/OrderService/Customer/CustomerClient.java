package com.alibou.OrderService.Customer;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(
        name = "CustomerService",
        url = "${application.config.customer-url}"
)
public interface CustomerClient {

    @GetMapping("/{customer-Id}")
    Optional<CustomerResponse> findById(@PathVariable("customer-Id") String customerId);

}
