package com.pvthach.foodproducer.stock.dto.request;


import java.io.Serializable;
import java.util.List;

/**
 * Created by THACH-PC
 */

public class StockMovingRequestDTO implements Serializable {

    private String createdBy;

    private String accApprovedBy;

    private List<StockItemRequestDTO> stockItems;

    private Integer totalPrice;


    public StockMovingRequestDTO() {
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

    public List<StockItemRequestDTO> getStockItems() {
        return stockItems;
    }

    public void setStockItems(List<StockItemRequestDTO> stockItems) {
        this.stockItems = stockItems;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }
}