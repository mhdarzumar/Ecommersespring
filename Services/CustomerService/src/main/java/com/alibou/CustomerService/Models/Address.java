package com.alibou.CustomerService.Models;


import lombok.*;
import org.springframework.validation.annotation.Validated;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
@Builder
@Validated
public class Address {

    private String houseNo;
    private String area;
    private String zipCode;
}
