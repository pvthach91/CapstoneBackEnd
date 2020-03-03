package com.pvthach.foodproducer.accounting.dto;


import java.io.Serializable;

/**
 * Created by THACH-PC
 */

public class PayslipDTO implements Serializable {
    private Long id;

    private String payslipType;

    private String ref;

    private Long totalPrice;

    private String createdBy;

    private String dateCreated;


    public PayslipDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPayslipType() {
        return payslipType;
    }

    public void setPayslipType(String payslipType) {
        this.payslipType = payslipType;
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
}