package com.pvthach.capstone.restaurant.dto;



import java.io.Serializable;
import java.util.List;

/**
 * Created by THACH-PC
 */

public class DishDTO implements Serializable {

    private Long id;

    private String name;

    private Integer price;

    private String description;

    private List<String> images;

    public DishDTO() {
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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
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
}