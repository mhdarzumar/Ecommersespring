package com.alibou.OrderService.OrderLine;

public record OrderLineRequest(

        Integer id,
        Integer orderId,
        Integer productId,
        Double quantity) {
}
