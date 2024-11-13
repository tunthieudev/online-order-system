package com.example.TakeOrder.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private int id;
    private int quantity;
    @JsonIgnore
    private double totalPrice;
}
