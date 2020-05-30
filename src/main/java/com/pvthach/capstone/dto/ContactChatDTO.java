package com.pvthach.capstone.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;

/**
 * Created by THACH-PC
 */

public class ContactChatDTO implements Serializable {
    private UserDTO user;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private String lastUpdated;

    public ContactChatDTO() {
    }

    public ContactChatDTO(UserDTO user, String lastUpdated) {
        this.user = user;
        this.lastUpdated = lastUpdated;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}