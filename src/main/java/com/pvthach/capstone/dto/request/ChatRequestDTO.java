package com.pvthach.capstone.dto.request;

import java.io.Serializable;

/**
 * Created by THACH-PC
 */

public class ChatRequestDTO implements Serializable {
    private String content;

    private Long toUser;

    public ChatRequestDTO() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getToUser() {
        return toUser;
    }

    public void setToUser(Long toUser) {
        this.toUser = toUser;
    }
}