package com.pvthach.foodproducer.stock.dto;


import java.io.Serializable;

/**
 * Created by THACH-PC
 */

public class IngredientSearchCriteria implements Serializable {

    private String name;

    private Integer quantityFrom;

    private Integer quantityTo;

    private Integer currentPage;

    private Integer pageSize;


    public IngredientSearchCriteria() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantityFrom() {
        return quantityFrom;
    }

    public void setQuantityFrom(Integer quantityFrom) {
        this.quantityFrom = quantityFrom;
    }

    public Integer getQuantityTo() {
        return quantityTo;
    }

    public void setQuantityTo(Integer quantityTo) {
        this.quantityTo = quantityTo;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}