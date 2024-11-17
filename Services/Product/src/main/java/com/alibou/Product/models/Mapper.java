package com.alibou.Product.models;


import org.springframework.stereotype.Service;

@Service
public class Mapper {



    public static Product toProduct( ProductRequest request) {

        return Product.builder()
                .id(request.id())
                .name(request.name())
                .price(request.price())
                .availableQuantity(request.availableQuantity())
                .description(request.description())
                .category(
                        Category.builder()
                                .id(request.category_id())
                                .build()
                )
                .build();
    }

    public ProductResponce toProductResponce(Product product) {
         return new ProductResponce(
                 product.getId(),
                 product.getName(),
                 product.getDescription(),
                 product.getPrice(),
                 product.getAvailableQuantity(),
                 product.getCategory().getId(),
                 product.getCategory().getName(),
                 product.getCategory().getDescription()
         );
    }

    public ProductPurchaseResponce toProductPurchaseResponce(Product availQuan, Double Quan) {

        return new ProductPurchaseResponce(
                availQuan.getId(),
                availQuan.getName(),
                availQuan.getDescription(),
                availQuan.getPrice(),
                Quan
        );
    }
}
