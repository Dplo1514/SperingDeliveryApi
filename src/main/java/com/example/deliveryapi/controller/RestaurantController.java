package com.example.deliveryapi.controller;

import com.example.deliveryapi.domain.Restaurant;
import com.example.deliveryapi.dto.RestaurantDto;
import com.example.deliveryapi.repository.RestaurantRepository;
import com.example.deliveryapi.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor //final 맵핑 생성자
@RestController
public class RestaurantController {

    private final RestaurantRepository restaurantRepository;
    private final RestaurantService restaurantService;

    @GetMapping("/restaurants")
    public List<Restaurant> getRestaurant(){
        return restaurantRepository.findAll();
    }


    @PostMapping("/restaurant/register")
    public ResponseEntity postRestaurant(@RequestBody RestaurantDto restaurantDto) {
        return new ResponseEntity<>(restaurantService.save(restaurantDto) , HttpStatus.OK);
    }
}
