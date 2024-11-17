package com.alibou.Product.Service;

import com.alibou.Product.Repository.ProductRepository;

import com.alibou.Product.models.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProductService {
    
    private final ProductRepository productRepo;
    private final Mapper Productmapper;
    
    public Integer createProduct(ProductRequest request) {
        var product = Mapper.toProduct(request);
        return productRepo.save(product).getId();
    }

    public List<ProductPurchaseResponce> PurchaseProducts(@NotNull List<ProductPurchaseRequest> request) {

        List<ProductPurchaseResponce> result = new ArrayList<>();

        var productIds = request
                .stream()
                .map(ProductPurchaseRequest::id)
                .toList();

        var storedProduct = this.productRepo.findAllByIdInOrderById(productIds);

        // Print the name and ID of each product in storedProduct
        System.out.println("Stored Products:");
        for (var product : storedProduct) {
            System.out.printf("Product ID: %d, Name: %s, Available Quantity: %.2f%n",
                    product.getId(),
                    product.getName(),
                    product.getAvailableQuantity());
            System.out.println();
        }


        if (productIds.size() != storedProduct.size()) {
            throw new PurchaseProductExeption("one or more item not found");
        }

        var reqProdusts = request
                .stream()
                .sorted(Comparator.comparing(ProductPurchaseRequest::id))
                .toList();

        for (int i = 0; i < storedProduct.size(); i++) {

            var requestQuan = reqProdusts.get(i);
            var availQuan = storedProduct.get(i);
            System.out.println(requestQuan.Quantity());
            System.out.println(availQuan.getAvailableQuantity());

            if (requestQuan.Quantity() > availQuan.getAvailableQuantity()) {
                throw new PurchaseProductExeption("quantity is low");
            }

            availQuan.setAvailableQuantity(availQuan.getAvailableQuantity() - requestQuan.Quantity());
            this.productRepo.save(availQuan);
            result.add(Productmapper.toProductPurchaseResponce(availQuan, requestQuan.Quantity()));
        }

        return result;
    }


    public ProductResponce getProduct(Integer id) {
        return productRepo.findById(id)
                .map(Productmapper::toProductResponce)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with ID:: " + id));
    }

    public List<ProductResponce> getAll()
    {
         return  this.productRepo.findAll().stream().map(Productmapper::toProductResponce).collect(Collectors.toList());
    }


}
