package com.pvthach.foodproducer.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by THACH-PC
 */

@Table(name = "USER")
@Entity(name = "User")
public class User implements Serializable {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME", nullable = false)
    @Size(min=3, max = 50)
    private String name;

    @Column(name = "USERNAME", nullable = false)
    @Size(min=3, max = 50)
    private String username;

    @Column(name = "EMAIL", nullable = false)
    @Size(max = 50)
    @Email
    private String email;

    @Column(name = "PASSWORD", nullable = false)
    @Size(min=6, max = 100)
    private String password;

    @Column(name = "PHONE", nullable = false)
    @Size(min=6, max = 100)
    private String phone;

    @JsonProperty("isActive")
    @Column(name = "IS_ACTIVE")
    private Boolean isActive;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "USER_ROLE",
    	joinColumns = @JoinColumn(name = "USER_ID"),
    	inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
    private Set<Role> roles = new HashSet<>();

    public User() {}

    public User(String name, String username, String email, String password, String phoneNumber, Boolean isActive) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.phone = phoneNumber;
        this.isActive = isActive;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

//    public String getEmployeeId() {
//        return employeeId;
//    }
//
//    public void setEmployeeId(String employeeId) {
//        this.employeeId = employeeId;
//    }

    public boolean containRole(long role) {
        for (Role r: roles) {
            if (r.getId()== role) {
                return true;
            }
        }
        return false;
    }
}