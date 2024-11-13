package com.example.TakeOrder.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CartDetail {
    private int id;
    private int quantity;
    private double price;
    private int productId;
    private int cartId;
}
