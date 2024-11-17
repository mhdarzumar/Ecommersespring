package com.alibou.Product.models;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Product {

    @Id
    @GeneratedValue
    private Integer id;
    
    private String name;
    
    private String description;
    
    private Double availableQuantity;
    
    private BigDecimal price;
    
    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;
    
}
