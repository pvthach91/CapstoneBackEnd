package com.pvthach.foodproducer.hr.dto.request;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.pvthach.foodproducer.hr.dto.EmployeeDTO;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by THACH-PC
 */

public class LeavingRequestDTO implements Serializable {

    private Long id;

    private Long employeeId;

    private String fromDate;

    private String toDate;

    private Integer dayOff;

    public LeavingRequestDTO() {
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

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public Integer getDayOff() {
        return dayOff;
    }

    public void setDayOff(Integer dayOff) {
        this.dayOff = dayOff;
    }
}