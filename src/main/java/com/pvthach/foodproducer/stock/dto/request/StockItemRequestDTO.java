package com.pvthach.foodproducer.stock.dto.request;


import com.pvthach.foodproducer.stock.dto.IngredientDTO;

import java.io.Serializable;

/**
 * Created by THACH-PC
 */

public class StockItemRequestDTO implements Serializable {

    private Long ingredientId;

    private Integer quantity;

    private Integer pricePerUnit;

    public StockItemRequestDTO() {
    }

    public Long getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(Long ingredientId) {
        this.ingredientId = ingredientId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(Integer pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }
}