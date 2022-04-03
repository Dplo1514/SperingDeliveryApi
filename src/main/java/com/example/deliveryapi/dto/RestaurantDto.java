package com.example.deliveryapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor  //모든 파라미터를 이용하는 생성자
@AllArgsConstructor //빈 파라미터파라미터 생성자
public class RestaurantDto {
    private Long id;

    private String name;

    private int minOrderPrice;

    private int deliveryFee;

}


