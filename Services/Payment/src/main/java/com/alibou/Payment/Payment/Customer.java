package com.alibou.Payment.Payment;

import jakarta.validation.constraints.NotNull;

public record Customer(

        String Id,

        @NotNull(message = "fname required")
        String firstName,

        @NotNull(message = "lname required")
        String lastName,

        @NotNull(message = "email required")
        String email
) {
}
