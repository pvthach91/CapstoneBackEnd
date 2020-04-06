package com.pvthach.capstone.farming.dto.request;


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