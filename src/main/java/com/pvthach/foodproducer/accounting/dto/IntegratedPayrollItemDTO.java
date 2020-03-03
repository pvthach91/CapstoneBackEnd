package com.pvthach.foodproducer.accounting.dto;


import com.pvthach.foodproducer.hr.dto.TimesheetDTO;

import java.io.Serializable;

/**
 * Created by THACH-PC
 */

public class IntegratedPayrollItemDTO implements Serializable {
    private Long id;

    private TimesheetDTO ref;

    private Integer salaryPerDay;

    public IntegratedPayrollItemDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TimesheetDTO getRef() {
        return ref;
    }

    public void setRef(TimesheetDTO ref) {
        this.ref = ref;
    }

    public Integer getSalaryPerDay() {
        return salaryPerDay;
    }

    public void setSalaryPerDay(Integer salaryPerDay) {
        this.salaryPerDay = salaryPerDay;
    }
}