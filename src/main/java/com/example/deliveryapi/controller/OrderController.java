package com.example.deliveryapi.controller;

import com.example.deliveryapi.domain.Order;
import com.example.deliveryapi.dto.OrderDto;
import com.example.deliveryapi.dto.OrderRequestDto;
import com.example.deliveryapi.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/order/request")
    public ResponseEntity<Order> getFood(@RequestBody OrderRequestDto orderDto) {
        return new ResponseEntity(orderService.post(orderDto) , HttpStatus.OK);
    }
    @GetMapping("/orders")
    public OrderDto[] getOrder(){
        return orderService.get();
    }
}
