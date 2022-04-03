package com.example.deliveryapi.service;

import com.example.deliveryapi.domain.Restaurant;
import com.example.deliveryapi.dto.RestaurantDto;
import com.example.deliveryapi.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    @Transactional
    public Restaurant save(RestaurantDto dto){

        restaurantValid(dto);

        Restaurant restaurant = Restaurant.builder()
                .name(dto.getName())
                .minOrderPrice(dto.getMinOrderPrice())
                .deliveryFee(dto.getDeliveryFee())
                .build();
        restaurantRepository.save(restaurant);
        return restaurant;
    }

    private void restaurantValid(RestaurantDto dto) {
        if (dto.getMinOrderPrice() % 100 != 0){
            throw new RuntimeException("100원단위로만 입력이 가능합니다.");
        }
        if (dto.getDeliveryFee() % 500 != 0){
            throw new RuntimeException("500원단위로만 입력이 가능합니다.");
        }

        if (dto.getMinOrderPrice() < 1000 || dto.getMinOrderPrice() > 100000){
            throw new RuntimeException("테스트");
        }

        if (dto.getDeliveryFee() < 0 || dto.getDeliveryFee() > 10000){
            throw new RuntimeException("테스트2");
        }
    }
}
