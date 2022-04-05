package com.example.deliveryapi.service;

import com.example.deliveryapi.domain.*;
import com.example.deliveryapi.dto.*;
import com.example.deliveryapi.repository.FoodOrdersRepository;
import com.example.deliveryapi.repository.FoodRepository;
import com.example.deliveryapi.repository.OrderRepository;
import com.example.deliveryapi.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OrderService {
    private final RestaurantRepository restaurantRepository;
    private final FoodRepository foodRepository;
    private final OrderRepository orderRepository;

    private final FoodOrdersRepository foodOrdersRepository;

    public Order post(OrderRequestDto orderRequestDto) {
        //레스토랑 id로 해당 음식점을 찾아온다.
        Restaurant restaurants = restaurantRepository.findById(orderRequestDto.getRestaurantId()).orElseThrow(() ->
                new IllegalArgumentException("음식점이 존재하지 않습니다."));
        //레스토랑 id로 해당 음식점의 음식을 찾아온다.
        //for문을 돌면서 음식점 id로 해당 음식점을 찾아오고 quantity로 해당 food를 찾아온다.
        //quantity로 찾아온 food를 기반 주문 정보를 작성한다.
        FoodOrders foodOrders = new FoodOrders();
        List<FoodOrders> foodOrdersList = new ArrayList<>();
        for (FoodOrderRequestDto idx : orderRequestDto.getFoods()){
            Food food = foodRepository.findByRestaurantAndId(restaurants, idx.getId());
            if (idx.getId().equals(food.getId())) {
                foodOrders = foodOrders.builder()
                        .name(food.getName())
                        .quantity(idx.getQuantity())
                        .price(food.getPrice() * idx.getQuantity())
                        .build();
                ValidCheckOrders(restaurants, foodOrders, idx);
                foodOrdersList.add(foodOrders);
                foodOrdersRepository.save(foodOrders);
            }
        }
//        식당이름 , {음식명 , 주문수량 , 음식가격 , 배달비} , 총가격(배달비 + 음식가격) 담아서 리턴해줘야함
        int total = 0;
        for(int i = 0 ; i < foodOrdersList.size(); i++){
            total += foodOrdersList.get(i).getPrice();
        }
        Order order = new Order();
            order = order.builder()
                    .restaurantName(restaurants.getName())
                    .foods(foodOrdersList)
                    .deliveryFee(restaurants.getDeliveryFee())
                    .totalPrice(restaurants.getDeliveryFee() + total)
                    .build();
        orderRepository.save(order);
        return order;
    }

    public Order[] get(){
        List<Order> orderList = orderRepository.findAll();
        System.out.println(orderList.size());
        Order[] orders = new Order[orderList.size()];
        for (int i = 0 ; i < orderList.size() ; i++){
             orders[i] =
                     orders[i].builder()
                    .restaurantName(orderList.get(i).getRestaurantName())
                    .foods(orderList.get(i).getFoods())
                    .deliveryFee(orderList.get(i).getDeliveryFee())
                    .totalPrice(orderList.get(i).getTotalPrice())
                    .build();
        }
        return orders;
    }

    private void ValidCheckOrders(Restaurant restaurants, FoodOrders foodOrders, FoodOrderRequestDto idx) {
        if (idx.getQuantity() < 1 || idx.getQuantity() > 100){
            throw new IllegalArgumentException("주문 수량 에러");
        }
        if (foodOrders.getPrice() < restaurants.getMinOrderPrice()){
            throw new IllegalArgumentException("최소 주문금액 에러");
        }
    }


    //스프링 테스트 트랜잭셔널
}
