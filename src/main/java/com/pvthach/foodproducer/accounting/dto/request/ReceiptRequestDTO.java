package com.pvthach.foodproducer.accounting.dto.request;


import java.io.Serializable;

/**
 * Created by THACH-PC
 */

public class ReceiptRequestDTO implements Serializable {

    private String receiptType;

    private String ref;

    private Long totalPrice;

    private String createdBy;

    public ReceiptRequestDTO() {
    }

    public String getReceiptType() {
        return receiptType;
    }

    public void setReceiptType(String receiptType) {
        this.receiptType = receiptType;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
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
}