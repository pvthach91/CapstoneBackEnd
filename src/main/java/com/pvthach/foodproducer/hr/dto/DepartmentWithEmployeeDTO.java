package com.pvthach.foodproducer.hr.dto;


import java.io.Serializable;
import java.util.List;

/**
 * Created by THACH-PC
 */

public class DepartmentWithEmployeeDTO implements Serializable {
    private Long id;

    private String name;

    private List<EmployeeDTO> employees;

    public DepartmentWithEmployeeDTO() {
    }

    public DepartmentWithEmployeeDTO(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<EmployeeDTO> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeDTO> employees) {
        this.employees = employees;
    }
}