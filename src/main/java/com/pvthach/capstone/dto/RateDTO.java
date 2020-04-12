package com.pvthach.capstone.dto;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by THACH-PC
 */

public class RateDTO implements Serializable {
    private Long id;

    private Integer star;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private String dateCreated;

    private UserDTO ratedBy;

    public RateDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStar() {
        return star;
    }

    public void setStar(Integer star) {
        this.star = star;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public UserDTO getRatedBy() {
        return ratedBy;
    }

    public void setRatedBy(UserDTO ratedBy) {
        this.ratedBy = ratedBy;
    }
}