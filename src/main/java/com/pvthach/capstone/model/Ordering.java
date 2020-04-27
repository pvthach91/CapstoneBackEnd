package com.pvthach.capstone.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pvthach.capstone.dto.OrderDTO;
import com.pvthach.capstone.ultil.DateFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

    @Column(name = "SHIPPING_METHOD", nullable = false)
    private String shippingMethod;

    @Column(name = "SHIPPING_PRICE", nullable = false)
    private Integer shippingPrice;


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

    public String getShippingMethod() {
        return shippingMethod;
    }

    public void setShippingMethod(String shippingMethod) {
        this.shippingMethod = shippingMethod;
    }

    public Integer getShippingPrice() {
        return shippingPrice;
    }

    public void setShippingPrice(Integer shippingPrice) {
        this.shippingPrice = shippingPrice;
    }

    public OrderDTO convertToDTO () {
        OrderDTO dto = new OrderDTO();
        dto.setId(id);
        dto.setOrderId(orderId);
        dto.setDate(DateFormat.format(date));
        dto.setOrderBy(orderBy.convertToDTO());
        dto.setAddress(address);
        dto.setLatitude(latitude);
        dto.setLongitude(longitude);
        dto.setTotalPrice(totalPrice);
        dto.setStatus(status);
        dto.setShippingMethod(shippingMethod);
        dto.setShippingPrice(shippingPrice);
        dto.setItems(OrderItem.convertToDTOs(items));

        return dto;
    }

    public static List<OrderDTO> convertToDTOs(List<Ordering> items) {
        List<OrderDTO> dtos = new ArrayList<OrderDTO>();
        for (Ordering item : items) {
            dtos.add(item.convertToDTO());
        }

        return dtos;
    }

    private String generateUniqueOrderId() {
        String pattern = "yyyyMMddHHmmss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String id = simpleDateFormat.format(new Date());

        return id;
    }
}