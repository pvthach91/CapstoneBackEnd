package com.pvthach.foodproducer.accounting.dto;


import java.io.Serializable;

/**
 * Created by THACH-PC
 */

public class ReceiptSearchCriteria implements Serializable {

    private String receiptType;

    private String ref;

    private Integer currentPage;

    private Integer pageSize;

    public ReceiptSearchCriteria() {
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