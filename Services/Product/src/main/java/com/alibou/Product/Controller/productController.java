package com.alibou.Product.Controller;




import com.alibou.Product.Service.ProductService;
import com.alibou.Product.models.*;
import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/products")
@RequiredArgsConstructor
public class productController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Integer> createProduct(
            @RequestBody @Valid ProductRequest Request
    )
    {
        return ResponseEntity.ok(this.productService.createProduct(Request));
    }

    @PostMapping("/purchase")
    public ResponseEntity<List<ProductPurchaseResponce>> purchaseProduct(@RequestBody List<ProductPurchaseRequest> request)
    {
        return ResponseEntity.ok(this.productService.PurchaseProducts(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponce> getProduct(@PathVariable("id") Integer id)
    {
        return ResponseEntity.ok(this.productService.getProduct(id));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponce>> getAllProducts()
    {
        return ResponseEntity.ok(this.productService.getAll());
    }



}
