package com.alibou.CustomerService.Models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.mongodb.core.aggregation.ArithmeticOperators;


public record CustomerRequest(
        String id,

        @NotNull(message = "should not null")
        String fname,

        @NotNull(message = "lanem reqired")
        String lname,

        @NotNull(message = "email is reqiured")
        @Email(message = "not a valid email")
        String email,

        Address address
) {

}
