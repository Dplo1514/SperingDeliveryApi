package com.example.deliveryapi.domain;

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
public class FoodOrderRequest {

    @Id
    private Long foodId;

    @Column
    private int quantity;

}
