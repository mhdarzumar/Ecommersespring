package com.alibou.OrderService.OrderLine;

import com.alibou.OrderService.OrderModel.Order;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Data
@Entity
@Builder
@Table(name = "order_line")
public class OrderLine {

    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    private Integer productID;

    private double quantity;
}
