package com.example.deliveryapi.domain;

import com.example.deliveryapi.dto.RestaurantDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@AllArgsConstructor //모든 필드값을 파라미터로 받는 생성자를 생성해준다.
@NoArgsConstructor //파라미터가 없는 기본 생성자를 생성해준다.
@Data
@Builder
@Entity
public class Restaurant {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int minOrderPrice;

    @Column(nullable = false)
    private int deliveryFee;

    //Restaurant Entity에서 foods Column으로 mapping , 아래 Column은 Restaurant에 Mapping된다.
    @OneToMany(mappedBy = "restaurant", fetch = FetchType.EAGER,  cascade = CascadeType.REMOVE)
    private List<Food> foods;

    public Restaurant(RestaurantDto restaurantDto) {
        this.id = restaurantDto.getId();
        this.name = restaurantDto.getName();
        this.minOrderPrice = restaurantDto.getMinOrderPrice();
        this.deliveryFee = restaurantDto.getDeliveryFee();
    }
}

