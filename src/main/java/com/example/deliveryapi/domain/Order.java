package com.example.deliveryapi.domain;
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
@Table(name = "order_request")
public class Order {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column
    //식당이름
    private String restaurantName;

    //{음식명 , 주문수량 , 음식가격 , 배달비}
    @OneToMany(mappedBy = "orderRequest" , cascade = CascadeType.REMOVE)
    private List<FoodOrders> foods;

    //배달비
    @Column
    private int deliveryFee;

    //총가격(배달비 + 음식가격)
    @Column
    private int totalPrice;

}
