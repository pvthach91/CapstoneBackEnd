package com.pvthach.foodproducer.hr.dto;



import java.io.Serializable;

/**
 * Created by THACH-PC
 */

public class TimesheetDTO implements Serializable {
    private Long id;

    private EmployeeDTO employee;

    private String dateCreated;

    private Integer workingHour;

    private String forDate;

    private String status;

    private String timesheetType;

    private String timesheetRef;

    public TimesheetDTO() {
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

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTimesheetType() {
        return timesheetType;
    }

    public void setTimesheetType(String timesheetType) {
        this.timesheetType = timesheetType;
    }

    public String getTimesheetRef() {
        return timesheetRef;
    }

    public void setTimesheetRef(String timesheetRef) {
        this.timesheetRef = timesheetRef;
    }
}