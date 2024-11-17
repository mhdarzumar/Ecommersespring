package com.alibou.Product.Repository;

import com.alibou.Product.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("SELECT p FROM Product p WHERE p.id IN :productIds ORDER BY p.id")
    List<Product> findAllByIdInOrderById(List<Integer> productIds);
}
