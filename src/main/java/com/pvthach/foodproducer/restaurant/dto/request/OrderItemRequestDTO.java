package com.pvthach.foodproducer.restaurant.dto.request;


import com.pvthach.foodproducer.restaurant.dto.DishDTO;

import java.io.Serializable;

/**
 * Created by THACH-PC
 */

public class OrderItemRequestDTO implements Serializable {

    private Long dishId;

    private Integer quantity;

    public OrderItemRequestDTO() {
    }

    public Long getDishId() {
        return dishId;
    }

    public void setDishId(Long dishId) {
        this.dishId = dishId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}