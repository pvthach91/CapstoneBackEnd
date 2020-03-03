package com.pvthach.foodproducer.hr.dto.request;


import java.io.Serializable;

/**
 * Created by THACH-PC
 */

public class TimesheetRequestDTO implements Serializable {
    private Long id;

    private Long employeeId;

    private Integer workingHour;

    private String forDate;

    public TimesheetRequestDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getWorkingHour() {
        return workingHour;
    }

    public void setWorkingHour(Integer workingHour) {
        this.workingHour = workingHour;
    }

    public String getForDate() {
        return forDate;
    }

    public void setForDate(String forDate) {
        this.forDate = forDate;
    }

}