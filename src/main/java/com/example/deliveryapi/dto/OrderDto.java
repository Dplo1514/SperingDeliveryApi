package com.example.deliveryapi.dto;

import com.example.deliveryapi.domain.FoodOrders;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class OrderDto {

    private String restaurantName;

    //{음식명 , 주문수량 , 음식가격 , 배달비}
    private List<FoodOrderDto> foods;

    //배달비
    private int deliveryFee;

    //총가격(배달비 + 음식가격)
    private int totalPrice;

}


