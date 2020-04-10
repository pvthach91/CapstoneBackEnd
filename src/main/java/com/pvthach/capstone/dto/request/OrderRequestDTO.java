package com.pvthach.capstone.dto.request;


import java.io.Serializable;
import java.util.List;

/**
 * Created by THACH-PC
 */

public class OrderRequestDTO implements Serializable {
    private String orderBy;

    private String email;

    private String phone;

    private String address;

    private Integer totalPrice;

    private List<OrderItemRequestDTO> items;

    public OrderRequestDTO() {
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<OrderItemRequestDTO> getItems() {
        return items;
    }

    public void setItems(List<OrderItemRequestDTO> items) {
        this.items = items;
    }
}