package com.pvthach.capstone.restaurant.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pvthach.capstone.restaurant.dto.OrderDTO;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by THACH-PC
 */

@Table(name = "ORDERING")
@Entity(name = "Ordering")
public class Ordering implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "ORDER_ID", nullable = false)
    private String orderId;

    @Column(name = "DATE")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date date;

    @Column(name = "ORDER_BY", nullable = false)
    private String orderBy;

    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Column(name = "PHONE", nullable = false)
    @Size(min = 3, max = 50)
    private String phone;

    @Column(name = "ADDRESS", nullable = false)
    private String address;

    @Column(name = "TOTAL_PRICE", nullable = false)
    private Integer totalPrice;

    @Column(name = "STATUS", nullable = false)
    private String status;

    @Column(name = "ORDER_TYPE")
    private String orderType;


    @JsonIgnoreProperties("ordering")
    @OneToMany(mappedBy = "ordering", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<OrderItem> items;

    public Ordering() {
        this.date = new Date();
        this.orderId = generateUniqueOrderId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public OrderDTO convertToDTO () {
        String pattern = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        OrderDTO dto = new OrderDTO();
        dto.setId(id);
        dto.setOrderId(orderId);
        dto.setDate(sdf.format(date));
        dto.setOrderBy(orderBy);
        dto.setEmail(email);
        dto.setPhone(phone);
        dto.setAddress(address);
        dto.setTotalPrice(totalPrice);
        dto.setStatus(status);
        dto.setOrderType(orderType);
        dto.setItems(OrderItem.convertToDTOs(items));

        return dto;
    }

    private String generateUniqueOrderId() {
        String pattern = "yyyyMMddHHmmss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String id = simpleDateFormat.format(new Date());

        return id;
    }
}