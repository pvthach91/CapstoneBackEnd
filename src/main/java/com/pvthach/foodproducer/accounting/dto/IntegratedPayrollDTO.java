package com.pvthach.foodproducer.accounting.dto;


import com.pvthach.foodproducer.hr.dto.EmployeeDTO;

import java.io.Serializable;
import java.util.List;

/**
 * Created by THACH-PC
 */

public class IntegratedPayrollDTO implements Serializable {

    private Long id;

    private EmployeeDTO employee;

    private Integer forYear;

    private Integer forMonth;

    private Long totalSalary;

    private List<IntegratedPayrollItemDTO> items;


    public IntegratedPayrollDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EmployeeDTO getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeDTO employee) {
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

    public List<IntegratedPayrollItemDTO> getItems() {
        return items;
    }

    public void setItems(List<IntegratedPayrollItemDTO> items) {
        this.items = items;
    }

}