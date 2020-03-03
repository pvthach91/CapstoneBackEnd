package com.pvthach.foodproducer.accounting.dto.request;


import java.io.Serializable;

/**
 * Created by THACH-PC
 */

public class PayslipRequestDTO implements Serializable {

    private String payslipType;

    private String ref;

    private Long totalPrice;

    private String createdBy;

    public PayslipRequestDTO() {
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
}