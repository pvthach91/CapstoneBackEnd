package com.pvthach.foodproducer.accounting.dto;


import java.io.Serializable;

/**
 * Created by THACH-PC
 */

public class PayslipSearchCriteria implements Serializable {

    private String payslipType;

    private String ref;

    private Integer currentPage;

    private Integer pageSize;

    public PayslipSearchCriteria() {
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