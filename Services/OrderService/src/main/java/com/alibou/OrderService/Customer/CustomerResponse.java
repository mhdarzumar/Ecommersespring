package com.alibou.OrderService.Customer;

public record CustomerResponse(

        String Id,
        String firstname,
        String lastname,
        String email
) {
}
