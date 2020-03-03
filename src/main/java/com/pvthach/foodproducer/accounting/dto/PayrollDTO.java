package com.pvthach.foodproducer.accounting.dto;


import java.io.Serializable;
import java.util.List;

/**
 * Created by THACH-PC
 */

public class PayrollDTO implements Serializable {

    private Long id;

    private String employee;

    private Integer forYear;

    private Integer forMonth;

    private Long totalSalary;

    private List<PayrollItemDTO> items;


    public PayrollDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
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

    public Long getTotalSalary() {
        return totalSalary;
    }

    public void setTotalSalary(Long totalSalary) {
        this.totalSalary = totalSalary;
    }

    public List<PayrollItemDTO> getItems() {
        return items;
    }

    public void setItems(List<PayrollItemDTO> items) {
        this.items = items;
    }

    public IntegratedPayrollDTO integrate() {
        IntegratedPayrollDTO result = new IntegratedPayrollDTO();
        result.setId(id);
        result.setForYear(forYear);
        result.setForMonth(forMonth);
        result.setTotalSalary(totalSalary);
        result.setItems(PayrollItemDTO.integrates(items));

        return result;
    }
}