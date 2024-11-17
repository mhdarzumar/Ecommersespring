package com.alibou.CustomerService.Models;

import org.springframework.stereotype.Service;

@Service
public class Mapper {


    public Customer toCustomer(CustomerRequest request) {
        if(request==null)
        {
            return null;
        }
        return Customer.builder()
                .id(request.id())
                .fanme(request.fname())
                .lname(request.lname())
                .email(request.email())
                .address(request.address())
                .build();
    }

    public CustomerResponce fromCustomer(Customer customer) {
        if(customer==null)
        {
            return null;
        }
        return new CustomerResponce(customer.getId(),
                customer.getFanme(),
                customer.getLname(),
                customer.getEmail(),
                customer.getAddress());
    }
}
