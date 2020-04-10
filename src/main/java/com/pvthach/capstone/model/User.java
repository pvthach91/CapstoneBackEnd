package com.pvthach.capstone.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.*;

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
    @Email
    private String email;

    @Column(name = "PASSWORD", nullable = false)
    @Size(min=6, max = 100)
    private String password;

    @Column(name = "PHONE", nullable = false)
    private String phone;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "JOIN_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date joinDate;

    @Column(name = "PHOTO")
    private String photo;

    @Column(name = "LATITUDE")
    private Double latitude;

    @Column(name = "LONGITUDE")
    private Double longitude;

    @JsonProperty("isActive")
    @Column(name = "IS_ACTIVE")
    private Boolean isActive;

//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    private List<Farm> farms = new ArrayList<Farm>();
//
//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    private List<Vehicle> vehicles = new ArrayList<Vehicle>();
//
//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    private List<Product> products = new ArrayList<Product>();
//
//    @OneToMany(mappedBy = "ratedBy", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    private List<Rate> rates = new ArrayList<Rate>();
//
//    @OneToMany(mappedBy = "orderBy", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    private List<Ordering> orders = new ArrayList<Ordering>();

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
        this.joinDate = new Date();
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

//    public List<Farm> getFarms() {
//        return farms;
//    }
//
//    public void setFarms(List<Farm> farms) {
//        this.farms = farms;
//    }
//
//    public List<Vehicle> getVehicles() {
//        return vehicles;
//    }
//
//    public void setVehicles(List<Vehicle> vehicles) {
//        this.vehicles = vehicles;
//    }
//
//    public List<Product> getProducts() {
//        return products;
//    }
//
//    public void setProducts(List<Product> products) {
//        this.products = products;
//    }
//
//    public List<Rate> getRates() {
//        return rates;
//    }
//
//    public void setRates(List<Rate> rates) {
//        this.rates = rates;
//    }
//
//    public List<Ordering> getOrders() {
//        return orders;
//    }
//
//    public void setOrders(List<Ordering> orders) {
//        this.orders = orders;
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