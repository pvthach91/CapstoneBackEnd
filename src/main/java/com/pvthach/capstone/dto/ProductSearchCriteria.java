package com.pvthach.capstone.dto;


import java.io.Serializable;
import java.util.List;

/**
 * Created by THACH-PC
 */

public class ProductSearchCriteria implements Serializable {

    private String name;

    private String status;

    private List<String> category;

    private String state;

    private Integer priceFrom;

    private Integer priceTo;

    private Boolean promotionActive;

    private Integer sort;


    public ProductSearchCriteria() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getCategory() {
        return category;
    }

    public void setCategory(List<String> category) {
        this.category = category;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getPriceFrom() {
        return priceFrom;
    }

    public void setPriceFrom(Integer priceFrom) {
        this.priceFrom = priceFrom;
    }

    public Integer getPriceTo() {
        return priceTo;
    }

    public void setPriceTo(Integer priceTo) {
        this.priceTo = priceTo;
    }

    public Boolean getPromotionActive() {
        return promotionActive;
    }

    public void setPromotionActive(Boolean promotionActive) {
        this.promotionActive = promotionActive;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}