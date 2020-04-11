package com.pvthach.capstone.dto;


import java.io.Serializable;

/**
 * Created by THACH-PC
 */

public class OrderItemDTO implements Serializable {
    private Long id;

    private ProductDTO product;

    private Integer quantity;

    public OrderItemDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}