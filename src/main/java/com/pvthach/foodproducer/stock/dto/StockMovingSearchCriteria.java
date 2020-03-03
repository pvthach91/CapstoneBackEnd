package com.pvthach.foodproducer.stock.dto;


import java.io.Serializable;

/**
 * Created by THACH-PC
 */

public class StockMovingSearchCriteria implements Serializable {

    private String stockRef;

    private String status;

    private Integer currentPage;

    private Integer pageSize;


    public StockMovingSearchCriteria() {
    }

    public String getStockRef() {
        return stockRef;
    }

    public void setStockRef(String stockRef) {
        this.stockRef = stockRef;
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