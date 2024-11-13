package com.example.TakeOrder.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class OrderDetail {
    private int id;
    private int quantity;
    private double price;
    private int productId;
    private int orderId;
}
