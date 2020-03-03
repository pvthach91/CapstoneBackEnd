package com.pvthach.foodproducer.accounting.dto;


import com.pvthach.foodproducer.hr.dto.TimesheetDTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by THACH-PC
 */

public class PayrollItemDTO implements Serializable {
    private Long id;

    private String ref;

    private Integer salaryPerDay;

    public PayrollItemDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public IntegratedPayrollItemDTO integrate() {
        IntegratedPayrollItemDTO result = new IntegratedPayrollItemDTO();
        result.setId(id);
        result.setSalaryPerDay(salaryPerDay);

        return result;
    }

    public static List<IntegratedPayrollItemDTO> integrates(List<PayrollItemDTO> list) {
        List<IntegratedPayrollItemDTO> result = new ArrayList<IntegratedPayrollItemDTO>();
        for (PayrollItemDTO dto: list) {
            result.add(dto.integrate());
        }

        return result;
    }
}