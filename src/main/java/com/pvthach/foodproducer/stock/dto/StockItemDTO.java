package com.pvthach.foodproducer.stock.dto;


import java.io.Serializable;

/**
 * Created by THACH-PC
 */

public class StockItemDTO implements Serializable {
    private Long id;

    private IngredientDTO ingredient;

    private Integer quantity;

    private Integer pricePerUnit;

    public StockItemDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public IngredientDTO getIngredient() {
        return ingredient;
    }

    public void setIngredient(IngredientDTO ingredient) {
        this.ingredient = ingredient;
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