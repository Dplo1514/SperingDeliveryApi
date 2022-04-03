package com.example.deliveryapi.controller;

import com.example.deliveryapi.domain.Food;
import com.example.deliveryapi.dto.FoodDto;
import com.example.deliveryapi.repository.FoodRepository;
import com.example.deliveryapi.repository.RestaurantRepository;
import com.example.deliveryapi.service.FoodService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class FoodController {
    private final FoodService foodService;

    @GetMapping("/restaurant/{restaurantId}/foods")
    public FoodDto[] getFood(@PathVariable Long restaurantId) {
        return foodService.get(restaurantId);
    }


    //음식점 ID 및 음식 정보 입력받아 등록
    @PostMapping("/restaurant/{restaurantId}/food/register")
    public ResponseEntity postFood(@PathVariable Long restaurantId , @RequestBody List<Food> dto) {
        foodService.save(dto,restaurantId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
