package com.example.deliveryapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderRequestDto {
    private Long restaurantId;
    private List<FoodOrderRequestDto> foods;
}
