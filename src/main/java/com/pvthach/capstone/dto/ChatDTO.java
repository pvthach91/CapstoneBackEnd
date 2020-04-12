package com.pvthach.capstone.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by THACH-PC
 */

public class ChatDTO implements Serializable {
    private Long id;

    private String content;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private String dateCreated;

    private UserDTO fromUser;

    private UserDTO toUser;

    private Boolean sourceVisible;

    private Boolean destinationVisible;

    public ChatDTO() {
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

    public UserDTO getFromUser() {
        return fromUser;
    }

    public void setFromUser(UserDTO fromUser) {
        this.fromUser = fromUser;
    }

    public UserDTO getToUser() {
        return toUser;
    }

    public void setToUser(UserDTO toUser) {
        this.toUser = toUser;
    }

    public Boolean getSourceVisible() {
        return sourceVisible;
    }

    public void setSourceVisible(Boolean sourceVisible) {
        this.sourceVisible = sourceVisible;
    }

    public Boolean getDestinationVisible() {
        return destinationVisible;
    }

    public void setDestinationVisible(Boolean destinationVisible) {
        this.destinationVisible = destinationVisible;
    }
}