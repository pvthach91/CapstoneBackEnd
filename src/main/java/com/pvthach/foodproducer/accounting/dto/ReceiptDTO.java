package com.pvthach.foodproducer.accounting.dto;


import com.pvthach.foodproducer.model.ReceiptIntegratedDTO;
import com.pvthach.foodproducer.restaurant.dto.OrderDTO;

import java.io.Serializable;

/**
 * Created by THACH-PC
 */

public class ReceiptDTO implements Serializable {
    private Long id;

    private String receiptType;

    private String ref;

    private Long totalPrice;

    private String createdBy;

    private String dateCreated;


    public ReceiptDTO() {
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

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public ReceiptIntegratedDTO convert() {
        ReceiptIntegratedDTO dto = new ReceiptIntegratedDTO();
        dto.setId(id);
        dto.setReceiptType(receiptType);
        dto.setTotalPrice(totalPrice);
        dto.setCreatedBy(createdBy);
        dto.setDateCreated(dateCreated);

        return dto;
    }
}