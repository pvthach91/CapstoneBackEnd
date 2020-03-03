package com.pvthach.foodproducer.restaurant.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created by THACH-PC
 */

@Table(name = "RESTAURANT")
@Entity(name = "Restaurant")
public class Restaurant implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;


    @Column(name = "NAME", nullable = false)
    @Size(min = 3, max = 50)
    private String name;

    @Column(name = "TOTAL_SEAT", nullable = false)
    @Size(min = 3, max = 50)
    private Integer totalSeat;

    @Column(name = "ADDRESS", nullable = false)
    private String address;

    public Restaurant() {
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

    public Integer getTotalSeat() {
        return totalSeat;
    }

    public void setTotalSeat(Integer totalSeat) {
        this.totalSeat = totalSeat;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}