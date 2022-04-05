package com.example.deliveryapi.repository;

import com.example.deliveryapi.domain.Food;
import com.example.deliveryapi.domain.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;


public interface FoodRepository extends JpaRepository<Food, Long> {
    Food findByRestaurantAndId(Restaurant restaurant , Long id);
}
