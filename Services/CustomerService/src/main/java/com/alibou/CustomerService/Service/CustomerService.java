package com.alibou.CustomerService.Service;


import com.alibou.CustomerService.Models.*;
import com.alibou.CustomerService.Repo.CustomerRepository;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {


    private final Mapper customerMapper;

    private final CustomerRepository customerRepository;

    public String createCustomer(CustomerRequest request) {
        var customer = customerRepository.save(customerMapper.toCustomer(request));
        return request.id();
    }

    public void updateOldCustomer(CustomerRequest request) {
        var cus = customerRepository.findById(request.id()).orElseThrow(
                ()-> new CustomerNotFoundException(
                        String.format("Cannot update customer:: No customer found with the provided ID: %s", request.id())));
        mergeCustomer(request,cus);
        customerRepository.save(cus);

    }

    private void mergeCustomer(CustomerRequest request, Customer cus) {

        if (StringUtils.isNotBlank(request.fname())) {
            cus.setFanme(request.fname());
        }

        if(StringUtils.isNotBlank(request.lname())){
            cus.setLname(request.lname());
        }

        if(request.address()!=null)
        {
            cus.setAddress(request.address());
        }

        if(StringUtils.isNotBlank(request.email()))
        {
            cus.setEmail(request.email());
        }

    }

    public List<CustomerResponce> findAllCustomers() {

        return customerRepository.findAll()
                .stream()
                .map(customerMapper::fromCustomer)
                .collect(Collectors.toList());
    }

    public Boolean existsById(String id) {
        return customerRepository.findById(id)
                .isPresent();
    }


    public CustomerResponce findById(String id) {
        return customerRepository.findById(id)
                .map(customerMapper::fromCustomer)
                .orElseThrow(() -> new CustomerNotFoundException(String.format("No customer found with the provided ID: %s", id)));
    }


    public void deleteById(String id) {
        this.customerRepository.deleteById(id);
    }
}
