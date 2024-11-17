package com.alibou.CustomerService.Models;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
@Builder
@Document
public class Customer {

    @Id
    private String id;

    private String fanme;
    private String lname;
    private String email;
    private Address address;

}
