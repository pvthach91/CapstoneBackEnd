package com.pvthach.capstone.dto;

import java.io.Serializable;

/**
 * Created by THACH-PC
 */

public class ShippingConfigDTO implements Serializable {
    private Long id;

    private String state;

    private Integer price;

    private Integer weightCarryFrom;

    private Integer weightCarryTo;

    public ShippingConfigDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getWeightCarryFrom() {
        return weightCarryFrom;
    }

    public void setWeightCarryFrom(Integer weightCarryFrom) {
        this.weightCarryFrom = weightCarryFrom;
    }

    public Integer getWeightCarryTo() {
        return weightCarryTo;
    }

    public void setWeightCarryTo(Integer weightCarryTo) {
        this.weightCarryTo = weightCarryTo;
    }
}