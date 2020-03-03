package com.pvthach.foodproducer.hr.dto;


import java.io.Serializable;

/**
 * Created by THACH-PC
 */

public class DepartmentDTO implements Serializable {
    private Long id;

    private String name;

    public DepartmentDTO() {
    }

    public DepartmentDTO(String name) {
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

}