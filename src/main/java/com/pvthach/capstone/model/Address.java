package com.pvthach.capstone.model;


import com.pvthach.capstone.dto.AddressDTO;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by THACH-PC
 */

@Table(name = "ADDRESS")
@Entity(name = "Address")
public class Address implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "STATE")
    private String state;

    @Column(name = "LATITUDE", nullable = false)
    private Double latitude;

    @Column(name = "LONGITUDE", nullable = false)
    private Double longitude;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="USER_ID")
    private User user;

    public Address() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AddressDTO convertToDTO() {
        AddressDTO dto = new AddressDTO();
        dto.setId(id);
        dto.setAddress(address);
        dto.setLatitude(latitude);
        dto.setLongitude(longitude);
        dto.setState(state);
        dto.setName(name);

        return dto;
    }

    public static List<AddressDTO> convertToDTOs(List<Address> dishes) {
        List<AddressDTO> dtos = new ArrayList<AddressDTO>();
        for (Address farm : dishes) {
            dtos.add(farm.convertToDTO());
        }
        return dtos;
    }
}