package com.example.deliveryapi.repository;

import com.example.deliveryapi.domain.Food;
import com.example.deliveryapi.domain.Restaurant;
import com.example.deliveryapi.dto.FoodDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant , Long> {
}
