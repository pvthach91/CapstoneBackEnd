package com.pvthach.capstone.model;


import com.pvthach.capstone.dto.ShippingConfigDTO;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by THACH-PC
 */

@Table(name = "SHIPPINGCONFIG")
@Entity(name = "ShippingConfig")
public class ShippingConfig implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "STATE", nullable = false)
    private String state;

    @Column(name = "PRICE", nullable = false)
    private Integer price;

    @Column(name = "WEIGHT_CARRY_FROM", nullable = false)
    private Integer weightCarryFrom;

    @Column(name = "WEIGHT_CARRY_TO", nullable = false)
    private Integer weightCarryTo;

    public ShippingConfig() {
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

    public ShippingConfigDTO convertToDTO() {
        ShippingConfigDTO dto = new ShippingConfigDTO();
        dto.setId(id);
        dto.setState(state);
        dto.setPrice(price);
        dto.setWeightCarryFrom(weightCarryFrom);
        dto.setWeightCarryTo(weightCarryTo);

        return dto;
    }

    public static List<ShippingConfigDTO> convertToDTOs(List<ShippingConfig> dishes) {
        List<ShippingConfigDTO> dtos = new ArrayList<ShippingConfigDTO>();
        for (ShippingConfig v : dishes) {
            dtos.add(v.convertToDTO());
        }
        return dtos;
    }
}