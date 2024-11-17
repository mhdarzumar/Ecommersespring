package com.alibou.OrderService.OrderLine;


import jakarta.ws.rs.Path;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/order-lines")
public class OrderLineController {

    private final OrderLineService Service;

    @GetMapping("/order/{order_id}")
    public ResponseEntity<List<OrderLineResponce>> getOrderLIneById(
            @PathVariable("order_id") Integer id
    )
    {
        return ResponseEntity.ok(Service.findOrderlineById(id));
    }
}