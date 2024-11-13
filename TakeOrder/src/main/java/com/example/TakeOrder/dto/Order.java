package com.example.TakeOrder.dto;

import java.text.DecimalFormat;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private int id;
    private int totalPrice;
    private int quantity;
    private String receiverName;
    private String receiverAddress;
    private String receiverPhone;
    private String receiverEmail;
    private String status;
    private String date;
    public String toString(){
        String s="\n";
        s+="Tổng giá: "+new DecimalFormat("###,###,###").format(totalPrice)+" đồng\n";
        s+="Số lượng: "+quantity+"\n";
        s+="Địa chỉ: "+receiverAddress+"\n";
        s+="Sđt: "+receiverPhone+"\n";
        return s;
    }
}
