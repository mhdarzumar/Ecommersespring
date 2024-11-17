package com.alibou.OrderService.OrderLine;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderLineService {

    private final OrderLineMapper mapper;
    private final OrderLineRepo repo;

    public void saveOrderLine(OrderLineRequest orderLineRequest) {
        var orderLine = this.mapper.toOrderLine(orderLineRequest);
        this.repo.save(orderLine);
    }

    public List<OrderLineResponce> findOrderlineById(Integer id) {

        return this.repo.findAllByOrderId(id)
                .stream()
                .map(this.mapper::toOrderLineResponce)
                .collect(Collectors.toList());

    }
}
