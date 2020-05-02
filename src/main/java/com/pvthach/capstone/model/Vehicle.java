package com.pvthach.capstone.model;


import com.pvthach.capstone.dto.VehicleDTO;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by THACH-PC
 */

@Table(name = "VEHICLE")
@Entity(name = "Vehicle")
public class Vehicle implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "PHOTO")
    private String photo;

    @Column(name = "PRICE_PER_KM", nullable = false)
    private Integer pricePerKm;

    @Column(name = "WEIGHT_CARRY", nullable = false)
    private Integer weightCarry;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="USER_ID")
    private User user;

    public Vehicle() {
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public VehicleDTO convertToDTO() {
        VehicleDTO dto = new VehicleDTO();
        dto.setId(id);
        dto.setName(name);
        dto.setPhoto(photo);
        dto.setPricePerKm(pricePerKm);
        dto.setWeightCarry(weightCarry);

        return dto;
    }

    public static List<VehicleDTO> convertToDTOs(List<Vehicle> dishes) {
        List<VehicleDTO> dtos = new ArrayList<VehicleDTO>();
        for (Vehicle v : dishes) {
            dtos.add(v.convertToDTO());
        }
        return dtos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return id.longValue() == vehicle.id.longValue();
    }
}