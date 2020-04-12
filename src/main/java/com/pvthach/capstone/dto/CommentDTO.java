package com.pvthach.capstone.dto;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by THACH-PC
 */

public class CommentDTO implements Serializable {
    private Long id;

    private String content;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private String dateCreated;

    private UserDTO commentedBy;

    public CommentDTO() {
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

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public UserDTO getCommentedBy() {
        return commentedBy;
    }

    public void setCommentedBy(UserDTO commentedBy) {
        this.commentedBy = commentedBy;
    }
}