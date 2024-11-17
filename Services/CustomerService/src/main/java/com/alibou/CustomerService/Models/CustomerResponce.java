package com.alibou.CustomerService.Models;

public record  CustomerResponce(
        String id,
        String fname,
        String lname,
        String email,
        Address address
) {

}
