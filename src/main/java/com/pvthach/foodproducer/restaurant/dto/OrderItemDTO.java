package com.pvthach.foodproducer.restaurant.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pvthach.foodproducer.restaurant.model.Dish;
import com.pvthach.foodproducer.restaurant.model.Ordering;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by THACH-PC
 */

public class OrderItemDTO implements Serializable {
    private Long id;

    private DishDTO dish;

    private Integer quantity;

    public OrderItemDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DishDTO getDish() {
        return dish;
    }

    public void setDish(DishDTO dish) {
        this.dish = dish;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}