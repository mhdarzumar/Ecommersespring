package com.alibou.CustomerService.Controller;

import com.alibou.CustomerService.Models.CustomerRequest;
import com.alibou.CustomerService.Models.CustomerResponce;
import com.alibou.CustomerService.Service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<String> addCustomer(@RequestBody @Valid CustomerRequest Request)
    {
        return ResponseEntity.ok(customerService.createCustomer(Request));
    }

    @PutMapping
    public ResponseEntity<Void> updateCustomer(@RequestBody @Valid CustomerRequest request)
    {
        this.customerService.updateOldCustomer(request);
        return ResponseEntity.accepted().build();
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponce>> getAllCustomers()
    {
        ResponseEntity<List<CustomerResponce>> ok = ResponseEntity.ok(customerService.findAllCustomers());
        return ok;
    }

    @GetMapping("/exists/{id}")
    public ResponseEntity<Boolean> isExists(@PathVariable("id") String id)
    {
        return ResponseEntity.ok(customerService.existsById(id));
    }

    @GetMapping("/{cus_id}")
    public ResponseEntity<CustomerResponce> findById(@PathVariable("cus_id") String id)
    {
        return ResponseEntity.ok(customerService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable("cus_id") String id)
    {
        this.customerService.deleteById(id);
        return ResponseEntity.accepted().build();
    }





}
