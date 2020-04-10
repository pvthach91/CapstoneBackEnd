//package com.pvthach.capstone.model;
//
//
//
//import com.fasterxml.jackson.annotation.JsonFormat;
//import com.pvthach.capstone.dto.DishDTO;
//
//import javax.persistence.*;
//import java.io.Serializable;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.Set;
//
///**
// * Created by THACH-PC
// */
//
//@Table(name = "DISH")
//@Entity(name = "Dish")
//public class Dish implements Serializable {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name = "ID")
//    private Long id;
//
//    @Column(name = "NAME", nullable = false)
//    private String name;
//
//    @Column(name = "PRICE", nullable = false)
//    private Integer price;
//
//    @Column(name = "DESCRIPTION", nullable = false)
//    private String description;
//
//    @Column(name = "IMAGES", nullable = false)
//    private String images;
//
//    @Column(name = "DATE_CREATED")
//    @Temporal(TemporalType.TIMESTAMP)
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
//    private Date dateCreated;
//
//    public Dish() {
//        this.dateCreated = new Date();
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public Integer getPrice() {
//        return price;
//    }
//
//    public void setPrice(Integer price) {
//        this.price = price;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public String getImages() {
//        return images;
//    }
//
//    public void setImages(String images) {
//        this.images = images;
//    }
//
//    public Date getDateCreated() {
//        return dateCreated;
//    }
//
//    public void setDateCreated(Date dateCreated) {
//        this.dateCreated = dateCreated;
//    }
//
//    public DishDTO convertToDTO() {
//        DishDTO dto = new DishDTO();
//        dto.setId(id);
//        dto.setName(name);
//        dto.setPrice(price);
//        dto.setDescription(description);
//        String[] imgArray = images.split(";");
//        List<String> imgList = new ArrayList<String>();
//        for (String img : imgArray) {
//            imgList.add(img);
//        }
//        dto.setImages(imgList);
//        return dto;
//    }
//
//    public static List<DishDTO> convertToDTO(Set<Dish> dishes) {
//        List<DishDTO> dtos = new ArrayList<DishDTO>();
//        for (Dish dish : dishes) {
//            dtos.add(dish.convertToDTO());
//        }
//        return dtos;
//    }
//}