package com.pvthach.capstone.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by THACH-PC
 */

public class SubCommentDTO implements Serializable {
    private Long id;

    private String content;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dateCreated;

    private UserDTO commentedBy;

    public SubCommentDTO() {
        this.dateCreated = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public UserDTO getCommentedBy() {
        return commentedBy;
    }

    public void setCommentedBy(UserDTO commentedBy) {
        this.commentedBy = commentedBy;
    }
}