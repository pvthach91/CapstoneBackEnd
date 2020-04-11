package com.pvthach.capstone.dto.request;


import java.io.Serializable;

/**
 * Created by THACH-PC
 */

public class OrderItemRequestDTO implements Serializable {

    private Long productId;

    private Integer quantity;

    public OrderItemRequestDTO() {
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}