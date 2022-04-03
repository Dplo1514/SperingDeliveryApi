package com.example.deliveryapi.service;

import com.example.deliveryapi.domain.Food;
import com.example.deliveryapi.domain.Restaurant;
import com.example.deliveryapi.dto.FoodDto;
import com.example.deliveryapi.repository.FoodRepository;
import com.example.deliveryapi.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FoodService {

    private final RestaurantRepository restaurantRepository;
    private final FoodRepository foodRepository;

    @Transactional
    public Food save(FoodDto dto , Long idx){
        //음식점의 id를 받아와 음식점을 idx
        Restaurant restaurants = restaurantRepository.findById(idx).orElseThrow(() ->
                new IllegalArgumentException("등록 가게가 올바르지 않습니다."));

        Food food = Food.builder()
                .name(dto.getName())
                .price(dto.getPrice())
                .restaurant(restaurants) //레스토랑의 아이디
                .build();
        foodRepository.save(food);

        List<Food> foods = restaurantRepository.getById(idx).getFoods();
        foodValid(dto, foods);
        return food;
    }

    private void foodValid(FoodDto dto, List<Food> foods) {
        if (dto.getName().equals(foods.stream().map(Food::getName))){
            throw new IllegalArgumentException("중복 이름 존재");
        }

        //허용값: 100원 ~ 1,000,000원
        if (dto.getPrice() < 100 || dto.getPrice() > 1000000){
            throw new IllegalArgumentException("가격 범위 미만 , 초과 에러");
        }
        if (dto.getPrice() % 100 != 0){
            throw new IllegalArgumentException("입력 단위 오류 에러");
        }
    }

}
