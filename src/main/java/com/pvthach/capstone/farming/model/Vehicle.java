package com.pvthach.capstone.farming.model;


import com.pvthach.capstone.admin.model.User;

import javax.persistence.*;
import java.io.Serializable;

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
}