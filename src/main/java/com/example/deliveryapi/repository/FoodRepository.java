package com.example.deliveryapi.repository;

import com.example.deliveryapi.domain.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food, Long> {
}
