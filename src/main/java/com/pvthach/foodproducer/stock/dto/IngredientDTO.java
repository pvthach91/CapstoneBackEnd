package com.pvthach.foodproducer.stock.dto;


import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by THACH-PC
 */

public class IngredientDTO implements Serializable {
    private Long id;

    private String name;

    private String unit;

    private Integer currentQuantity;

    public IngredientDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getCurrentQuantity() {
        return currentQuantity;
    }

    public void setCurrentQuantity(Integer currentQuantity) {
        this.currentQuantity = currentQuantity;
    }
}