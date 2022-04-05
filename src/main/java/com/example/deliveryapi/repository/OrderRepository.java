package com.example.deliveryapi.repository;

import com.example.deliveryapi.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order , Long> {
}
