package com.alibou.CustomerService.Repo;

import com.alibou.CustomerService.Models.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomerRepository extends MongoRepository<Customer,String> {
}
