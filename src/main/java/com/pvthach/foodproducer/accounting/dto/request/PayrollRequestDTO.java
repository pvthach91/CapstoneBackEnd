package com.pvthach.foodproducer.accounting.dto.request;


import java.io.Serializable;

/**
 * Created by THACH-PC
 */

public class PayrollRequestDTO implements Serializable {

    private String employeeId;

    private Integer forYear;

    private Integer forMonth;


    public PayrollRequestDTO() {
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getForYear() {
        return forYear;
    }

    public void setForYear(Integer forYear) {
        this.forYear = forYear;
    }

    public Integer getForMonth() {
        return forMonth;
    }

    public void setForMonth(Integer forMonth) {
        this.forMonth = forMonth;
    }
}