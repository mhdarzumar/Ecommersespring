package com.alibou.OrderService.OrderModel;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final  OrderService orderService;

    @PostMapping
    public ResponseEntity<Integer> createOrder(
            @RequestBody @Valid OrderRequest Request
    )
    {
        return ResponseEntity.ok(orderService.createOrder(Request));
    }

    @GetMapping("/{order_id}")
    public ResponseEntity<OrderResponce> findOrderById(
            @PathVariable("order_id") Integer id
    )
    {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @GetMapping
    public ResponseEntity<List<OrderResponce>> findAllOrder()
    {
        return ResponseEntity.ok(this.orderService.getAllOrder());
    }



}
