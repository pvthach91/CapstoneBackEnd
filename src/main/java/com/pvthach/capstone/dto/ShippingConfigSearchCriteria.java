package com.pvthach.capstone.dto;


import java.io.Serializable;

/**
 * Created by THACH-PC
 */

public class ShippingConfigSearchCriteria implements Serializable {

    private String state;

    private Integer currentPage;

    private Integer pageSize;

    public ShippingConfigSearchCriteria() {
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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