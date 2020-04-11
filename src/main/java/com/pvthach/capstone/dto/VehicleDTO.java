package com.pvthach.capstone.dto;

import java.io.Serializable;

/**
 * Created by THACH-PC
 */

public class VehicleDTO implements Serializable {
    private Long id;

    private String name;

    private String photo;

    private Integer pricePerKm;

    private Integer weightCarry;

    public VehicleDTO() {
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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Integer getPricePerKm() {
        return pricePerKm;
    }

    public void setPricePerKm(Integer pricePerKm) {
        this.pricePerKm = pricePerKm;
    }

    public Integer getWeightCarry() {
        return weightCarry;
    }

    public void setWeightCarry(Integer weightCarry) {
        this.weightCarry = weightCarry;
    }
}