package com.pvthach.foodproducer.hr.dto;


import java.io.Serializable;

/**
 * Created by THACH-PC
 */

public class LeavingSearchCriteria implements Serializable {

    private String employeeName;

    private String status;

    private Integer currentPage;

    private Integer pageSize;

    public LeavingSearchCriteria() {
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
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