package com.example.deliveryapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor  //모든 파라미터를 이용하는 생성자
@AllArgsConstructor //빈 파라미터파라미터 생성자
@Builder
//음식명 (name) , 주문 수량 (quantity) , 주문 음식의 가격 (price)
public class FoodOrderDto {
    private String name;
    private int quantity;
    private int price;
}
