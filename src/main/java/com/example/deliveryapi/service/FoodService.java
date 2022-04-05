package com.example.deliveryapi.service;

import com.example.deliveryapi.domain.Food;
import com.example.deliveryapi.domain.Restaurant;
import com.example.deliveryapi.dto.FoodDto;
import com.example.deliveryapi.repository.FoodRepository;
import com.example.deliveryapi.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@RequiredArgsConstructor
@Service
public class FoodService {

    private final RestaurantRepository restaurantRepository;
    private final FoodRepository foodRepository;

    @Transactional
    public FoodDto[] get(Long restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(
                () -> new IllegalArgumentException("음식점이 존재하지 않습니다.")
        );
        //1 해당 레스토랑의 food를 List로 가져온다.
        List<Food> RestaurantfoodList = restaurant.getFoods();
        //2가져온 foodList를 foodDto에 build를통해 할당
        FoodDto[] foodDtos = new FoodDto[RestaurantfoodList.size()];
        for (int i = 0; i < foodDtos.length; i++) {
            foodDtos[i] =
                    foodDtos[i].builder()
                            .id(RestaurantfoodList.get(i).getId())
                            .name(RestaurantfoodList.get(i).getName())
                            .price(RestaurantfoodList.get(i).getPrice())
                            .build();
        }
        return foodDtos;
    }

    @Transactional
    public void save(List<Food> dto, Long idx) {
        //클라이언트에서 보내준 음식점의 id로 음식점을 찾는다.
        Restaurant restaurants = restaurantRepository.findById(idx).orElseThrow(() ->
                new IllegalArgumentException("음식점이 존재하지 않습니다."));
        //음식점의 id값으로 찾은 음식점의 food의 이름을 찾아 list로 만들어준다.
        List<Food> alreadyRestaurantFood = new ArrayList<>(restaurants.getFoods());
        //가져온 food의 이름만 저장할 빈 배열 생성
        List<String> alreadyRestaurnatFoodName = new ArrayList<>();

        for (Food name : alreadyRestaurantFood) {
            alreadyRestaurnatFoodName.add(name.getName());
        }

        //입력받은 food의 이름만 할당할 객체 생성
        List<String> newFoodName = new ArrayList<>();
        //새로운 food 객체에 입력받은 List dto의 값을 반복하며 할당한 후 저장한다.
        Food food = new Food();
        for (Food foodDto : dto) {
            food = Food.builder()
                    .id(null)
                    .name(foodDto.getName())
                    .price(foodDto.getPrice())
                    .restaurant(restaurants) //레스토랑의 아이디
                    .build();
            //Input값 food와 기존값의 비교를 위하여 새로운 리스트에 할당
            newFoodName.add(foodDto.getName());
            ValidCheckFood(food, alreadyRestaurnatFoodName, newFoodName);
            foodRepository.save(food);
        }
    }

    private void ValidCheckFood(Food food, List<String> alreadyRestaurantFoodName, List<String> newFoodName) {

        //동일 음식 등록 에러
        Set<String> matchFoodName = new HashSet<>(newFoodName);
        if (newFoodName.size() != matchFoodName.size()) {
            throw new IllegalArgumentException("동일 음식등록 에러");
        }

        //기존 저장된 음식명과 중복
        if (alreadyRestaurantFoodName.stream().anyMatch(newFoodName::contains)) {
            throw new IllegalArgumentException("이미 저장된 음식명 에러");
        }

        //허용값: 100원 ~ 1,000,000원
        if (food.getPrice() < 100 || food.getPrice() > 1000000) {
            throw new IllegalArgumentException("가격 범위 미만 , 초과 에러");
        }
        if (food.getPrice() % 100 != 0) {
            throw new IllegalArgumentException("입력 단위 오류 에러");
        }
    }

}

