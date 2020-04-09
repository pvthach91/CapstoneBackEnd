package com.pvthach.capstone.farming.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by THACH-PC
 */

@Table(name = "ORDERITEM")
@Entity(name = "OrderItem")
public class OrderItem implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @OneToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    @Column(name = "QUANTITY", nullable = false)
    private Integer quantity;

    @JsonIgnoreProperties("orderitem")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="ORDER_ID")
    private Ordering ordering;

    public OrderItem() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Ordering getOrdering() {
        return ordering;
    }

    public void setOrdering(Ordering ordering) {
        this.ordering = ordering;
    }

//    public OrderItemDTO convertToDTO() {
//        OrderItemDTO dto = new OrderItemDTO();
//        dto.setId(id);
//        dto.setQuantity(quantity);
//        dto.setDish(dish.convertToDTO());
//
//        return dto;
//    }

//    public static List<OrderItemDTO> convertToDTOs(List<OrderItem> items) {
//        List<OrderItemDTO> dtos = new ArrayList<OrderItemDTO>();
//        for (OrderItem item : items) {
//            dtos.add(item.convertToDTO());
//        }
//
//        return dtos;
//    }
}