package com.pvthach.foodproducer.accounting.dto.request;


import java.io.Serializable;

/**
 * Created by THACH-PC
 */

public class PayrollItemRequestDTO implements Serializable {
    private String ref;

    private Integer salaryPerDay;

    public PayrollItemRequestDTO() {
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public Integer getSalaryPerDay() {
        return salaryPerDay;
    }

    public void setSalaryPerDay(Integer salaryPerDay) {
        this.salaryPerDay = salaryPerDay;
    }
}