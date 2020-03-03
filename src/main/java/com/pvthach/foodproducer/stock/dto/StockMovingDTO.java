package com.pvthach.foodproducer.stock.dto;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by THACH-PC
 */

public class StockMovingDTO implements Serializable {
    private Long id;

    private String stockRef;

    private String stockType;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date date;

    private List<StockItemDTO> stockItems;

    private Integer totalPrice;

    private String status;

    private String createdBy;

    private String accApprovedBy;

    public StockMovingDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStockRef() {
        return stockRef;
    }

    public void setStockRef(String stockRef) {
        this.stockRef = stockRef;
    }

    public String getStockType() {
        return stockType;
    }

    public void setStockType(String stockType) {
        this.stockType = stockType;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<StockItemDTO> getStockItems() {
        return stockItems;
    }

    public void setStockItems(List<StockItemDTO> stockItems) {
        this.stockItems = stockItems;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getAccApprovedBy() {
        return accApprovedBy;
    }

    public void setAccApprovedBy(String accApprovedBy) {
        this.accApprovedBy = accApprovedBy;
    }
}