package com.example.deliveryapi.domain;

import com.example.deliveryapi.dto.FoodDto;
import com.example.deliveryapi.dto.RestaurantDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@AllArgsConstructor //모든 필드값을 파라미터로 받는 생성자를 생성해준다.
@NoArgsConstructor //파라미터가 없는 기본 생성자를 생성해준다.
@Data
@Builder
@Entity
@Table(name = "foods")
public class Food {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column
    private String name;

    @Column
    private int price;

    //Food는 Restaurant와 manytoone관계를 갖게된다.
    //@JoinColumn : FK를 가지는 엔티티가 @JoinColumn 어노테이션을 사용합니다.
    //논리적으로 Food가 어떤 카테고리에 속하는지를 식별해야하기 때문에 Food가 FK를 가집니다.
    //name="restaurant_id" : 이것이 실제 Restaurant 테이블에 있는 Food테이블의 FK 컬럼명이 된다.
    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    public Food(FoodDto foodDto) {
        this.id = foodDto.getId();
        this.name = foodDto.getName();
        this.price = foodDto.getPrice();
        this.restaurant = foodDto.getRestaurant();
    }
}
