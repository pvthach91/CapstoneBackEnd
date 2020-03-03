package com.pvthach.foodproducer.model;


import com.pvthach.foodproducer.restaurant.dto.OrderDTO;

import java.io.Serializable;

/**
 * Created by THACH-PC
 */

public class ReceiptIntegratedDTO implements Serializable {
    private Long id;

    private String receiptType;

    private OrderDTO ref;

    private Long totalPrice;

    private String createdBy;

    private String dateCreated;


    public ReceiptIntegratedDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReceiptType() {
        return receiptType;
    }

    public void setReceiptType(String receiptType) {
        this.receiptType = receiptType;
    }

    public OrderDTO getRef() {
        return ref;
    }

    public void setRef(OrderDTO ref) {
        this.ref = ref;
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }
}