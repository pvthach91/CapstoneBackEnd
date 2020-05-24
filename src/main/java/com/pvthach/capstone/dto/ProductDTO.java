package com.pvthach.capstone.dto;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.List;

/**
 * Created by THACH-PC
 */

public class ProductDTO implements Serializable {
    private Long id;

    private String name;

    private String category;

    private Integer price;

    private Integer promotionPrice;

    private Boolean promotionActive;

    private String description;

    private List<String> images;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private String dateCreated;

    private Double latitude;

    private Double longitude;

    private UserDTO user;

    private Integer quantity;

    private Boolean storeLocation;

    private Long locationRef;

    private String status;

    private Integer totalOrder;

    public ProductDTO() {
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getPromotionPrice() {
        return promotionPrice;
    }

    public void setPromotionPrice(Integer promotionPrice) {
        this.promotionPrice = promotionPrice;
    }

    public Boolean getPromotionActive() {
        return promotionActive;
    }

    public void setPromotionActive(Boolean promotionActive) {
        this.promotionActive = promotionActive;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
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

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Boolean getStoreLocation() {
        return storeLocation;
    }

    public void setStoreLocation(Boolean storeLocation) {
        this.storeLocation = storeLocation;
    }

    public Long getLocationRef() {
        return locationRef;
    }

    public void setLocationRef(Long locationRef) {
        this.locationRef = locationRef;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getTotalOrder() {
        return totalOrder;
    }

    public void setTotalOrder(Integer totalOrder) {
        this.totalOrder = totalOrder;
    }
}