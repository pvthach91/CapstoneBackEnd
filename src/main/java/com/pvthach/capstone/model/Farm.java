package com.pvthach.capstone.model;


import com.pvthach.capstone.dto.FarmDTO;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by THACH-PC
 */

@Table(name = "Farm")
@Entity(name = "Farm")
public class Farm implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "STATE")
    private String state;

    @Column(name = "IMAGES")
    private String images;

    @Column(name = "LATITUDE", nullable = false)
    private Double latitude;

    @Column(name = "LONGITUDE", nullable = false)
    private Double longitude;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="USER_ID")
    private User user;

    public Farm() {
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

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
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

    public FarmDTO convertToDTO() {
        FarmDTO dto = new FarmDTO();
        dto.setId(id);
        dto.setAddress(address);
        String[] imgArray = images.split(";");
        List<String> imgList = new ArrayList<String>();
        for (String img : imgArray) {
            imgList.add(img);
        }
        dto.setImages(imgList);
        dto.setLatitude(latitude);
        dto.setLongitude(longitude);
        dto.setState(state);

        return dto;
    }

    public static List<FarmDTO> convertToDTOs(List<Farm> dishes) {
        List<FarmDTO> dtos = new ArrayList<FarmDTO>();
        for (Farm farm : dishes) {
            dtos.add(farm.convertToDTO());
        }
        return dtos;
    }
}