package com.pvthach.capstone.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.pvthach.capstone.dto.ProductDTO;
import com.pvthach.capstone.ultil.DateFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by THACH-PC
 */

@Table(name = "PRODUCT")
@Entity(name = "Product")
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "CATEGORY", nullable = false)
    private String category;

    @Column(name = "PRICE", nullable = false)
    private Integer price;

    @Column(name = "PROMOTION_PRICE", nullable = false)
    private Integer promotionPrice;

    @Column(name = "PROMOTION_ACTIVE", nullable = false)
    private Boolean promotionActive;

    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    @Column(name = "IMAGES", nullable = false)
    private String images;

    @Column(name = "DATE_CREATED")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dateCreated;

    @Column(name = "LATITUDE", nullable = false)
    private Double latitude;

    @Column(name = "LONGITUDE", nullable = false)
    private Double longitude;

    @Column(name = "QUANTITY", nullable = false)
    private Integer quantity;

    @Column(name = "STORE_LOCATION")
    private Boolean storeLocation;

    @Column(name = "LOCATION_REF")
    private Long locationRef;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="USER_ID")
    private User user;

    public Product() {
        this.dateCreated = new Date();
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

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
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

    public ProductDTO convertToDTO() {
        ProductDTO dto = new ProductDTO();
        dto.setId(id);
        dto.setName(name);
        dto.setCategory(category);
        dto.setPrice(price);
        dto.setPromotionPrice(promotionPrice);
        dto.setPromotionActive(promotionActive);
        dto.setDescription(description);
        String[] imgArray = images.split(";");
        List<String> imgList = new ArrayList<String>();
        for (String img : imgArray) {
            imgList.add(img);
        }
        dto.setImages(imgList);
        dto.setDateCreated(DateFormat.format(dateCreated));
        dto.setLatitude(latitude);
        dto.setLongitude(longitude);
        dto.setUser(user.convertToDTO());
        dto.setQuantity(quantity);
        dto.setStoreLocation(storeLocation);
        dto.setLocationRef(locationRef);

        return dto;
    }

    public static List<ProductDTO> convertToDTOs(List<Product> list) {
        List<ProductDTO> result = new ArrayList<ProductDTO>();
        for (Product p : list) {
            result.add(p.convertToDTO());
        }

        return result;
    }

}