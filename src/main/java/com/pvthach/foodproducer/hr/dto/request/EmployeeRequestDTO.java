package com.pvthach.foodproducer.hr.dto.request;



import java.io.Serializable;

/**
 * Created by THACH-PC
 */

public class EmployeeRequestDTO implements Serializable {
    private Long id;

    private Long departmentId;

    private Boolean sex;

    private String name;

    private String address;

    private String phone;

    private String dob;

    private String joinDate;

    private String photo;

    private Integer salaryRate;

    private Integer currentDayOff;

    public EmployeeRequestDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
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
