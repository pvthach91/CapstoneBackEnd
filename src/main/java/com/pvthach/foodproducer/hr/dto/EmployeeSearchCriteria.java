package com.pvthach.foodproducer.hr.dto;


import java.io.Serializable;

/**
 * Created by THACH-PC
 */

public class EmployeeSearchCriteria implements Serializable {

    private String name;

    private String department;

    private Integer currentPage;

    private Integer pageSize;

    public EmployeeSearchCriteria() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
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