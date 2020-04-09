package com.pvthach.capstone.farming.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pvthach.capstone.admin.model.User;

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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="ORDER_BY")
    private User orderBy;

    @Column(name = "ADDRESS", nullable = false)
    private String address;

    @Column(name = "LATITUDE", nullable = false)
    private Double latitude;

    @Column(name = "LONGITUDE", nullable = false)
    private Double longitude;

    @Column(name = "TOTAL_PRICE", nullable = false)
    private Integer totalPrice;

    @Column(name = "STATUS", nullable = false)
    private String status;


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

    public User getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(User orderBy) {
        this.orderBy = orderBy;
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

//    public OrderDTO convertToDTO () {
//        String pattern = "yyyy-MM-dd HH:mm:ss";
//        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
//        OrderDTO dto = new OrderDTO();
//        dto.setId(id);
//        dto.setOrderId(orderId);
//        dto.setDate(sdf.format(date));
////        dto.setOrderBy(orderBy);
////        dto.setEmail(email);
////        dto.setPhone(phone);
//        dto.setAddress(address);
//        dto.setTotalPrice(totalPrice);
//        dto.setStatus(status);
////        dto.setOrderType(orderType);
//        dto.setItems(OrderItem.convertToDTOs(items));
//
//        return dto;
//    }

    private String generateUniqueOrderId() {
        String pattern = "yyyyMMddHHmmss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String id = simpleDateFormat.format(new Date());

        return id;
    }
}