package com.pvthach.capstone.farming.dto;


import java.io.Serializable;

/**
 * Created by THACH-PC
 */

public class OrderSearchCriteria implements Serializable {

    private String orderId;

    private String status;

    private Integer currentPage;

    private Integer pageSize;


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
}