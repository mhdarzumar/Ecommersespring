package com.alibou.OrderService.OrderModel;


import com.alibou.OrderService.Customer.CustomerClient;
import com.alibou.OrderService.Exeption.BusinessException;
import com.alibou.OrderService.OrderLine.OrderLineRequest;
import com.alibou.OrderService.OrderLine.OrderLineService;
import com.alibou.OrderService.PaymentClient.PaymentClient;
import com.alibou.OrderService.PaymentClient.PaymentRequest;
import com.alibou.OrderService.Product.ProductClient;
import com.alibou.OrderService.Product.PurchaseRequest;
import com.alibou.OrderService.kafka.OrderConfirmation;
import com.alibou.OrderService.kafka.OrderProducer;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.plaf.BorderUIResource;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderProducer orderProducer;
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final OrderLineService orderLineService;
    private final PaymentClient paymentClient;

    public Integer createOrder(OrderRequest request) {
        //Customer present or not

        var customer = this.customerClient.findById(request.customerId()).orElseThrow(()->new BusinessException("Cannot create order:: No customer exists with the provided ID"));

        var purchasedProduct = this.productClient.purchaseProduct(request.purchaseList());

        var order = this.orderRepository.save(orderMapper.toOrder(request));

        for(PurchaseRequest req:request.purchaseList())
        {
             orderLineService.saveOrderLine(
                     new OrderLineRequest(
                             null,
                             order.getId(),
                             req.id(),
                             req.Quantity()
                     )
             );
        }
        var paymentRequest = new PaymentRequest(
                request.totalAmount(),
                request.paymentMethod(),
                order.getId(),
                order.getReference(),
                customer
        );

        paymentClient.CreatePayment(paymentRequest);

        orderProducer.sendOrderConfirmation(
                new OrderConfirmation(
                                        request.reference(),
                                        request.totalAmount(),
                                        request.paymentMethod(),
                        customer,
                        purchasedProduct
                                )
        );
        return  order.getId();
    }

    public List<OrderResponce> getAllOrder() {
        return this.orderRepository.findAll()
                .stream()
                .map(orderMapper::toOrderResponce)
                .collect(Collectors.toList());
    }

    public OrderResponce getOrderById(Integer id) {
        return this.orderRepository.findById(id).map(orderMapper::toOrderResponce)
                .orElseThrow(()-> new EntityNotFoundException(String.format("No order found with the provided ID: %d", id)));
    }
}
