package com.example.deliveryapi.repository;

import com.example.deliveryapi.domain.FoodOrders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodOrdersRepository extends JpaRepository<FoodOrders , Long> {
}
