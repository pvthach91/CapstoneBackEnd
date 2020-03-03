package com.pvthach.foodproducer.hr.dto;


import java.io.Serializable;

/**
 * Created by THACH-PC
 */

public class EmployeeDTO implements Serializable {
    private Long id;

    private DepartmentDTO department;

    private Boolean sex;

    private String employeeId;

    private String name;

    private String address;

    private String phone;

//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private String dob;

//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private String joinDate;

    private String photo;

    private Integer salaryRate;

    private Integer currentDayOff;

    public EmployeeDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public DepartmentDTO getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentDTO department) {
        this.department = department;
    }

    public Integer getSalaryRate() {
        return salaryRate;
    }

    public void setSalaryRate(Integer salaryRate) {
        this.salaryRate = salaryRate;
    }

    public Integer getCurrentDayOff() {
        return currentDayOff;
    }

    public void setCurrentDayOff(Integer currentDayOff) {
        this.currentDayOff = currentDayOff;
    }
}
