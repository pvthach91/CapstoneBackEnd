package com.pvthach.capstone.dto;


import java.io.Serializable;

/**
 * Created by THACH-PC
 */

public class OrderSearchCriteria implements Serializable {

    private String orderId;

    private String status;

    private Integer currentPage;

    private Integer pageSize;

    private String orderBy;

    private String farmer;


    public OrderSearchCriteria() {
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getFarmer() {
        return farmer;
    }

    public void setFarmer(String farmer) {
        this.farmer = farmer;
    }
}