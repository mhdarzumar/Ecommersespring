package com.alibou.OrderService.OrderLine;

import com.alibou.OrderService.OrderModel.Order;
import com.alibou.OrderService.OrderModel.OrderResponce;
import org.springframework.stereotype.Service;

@Service
public class OrderLineMapper {


    public OrderLine toOrderLine(OrderLineRequest orderLineRequest) {
        return OrderLine.builder()
                .id(orderLineRequest.id())
                .productID(orderLineRequest.productId())
                .order(
                        Order.builder()
                                .id(orderLineRequest.orderId())
                                .build()
                )
                .quantity(orderLineRequest.quantity())
                .build();
    }

    public OrderLineResponce toOrderLineResponce(OrderLine OrderLine)
    {
        return new OrderLineResponce(
                OrderLine.getId(),
                OrderLine.getQuantity()
        );
    }


}
