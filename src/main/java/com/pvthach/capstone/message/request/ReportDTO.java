package com.pvthach.capstone.message.request;

import java.io.Serializable;

/**
 * Created by THACH-PC
 */

public class ReportDTO implements Serializable {
    private Long id;

    private String comment;

    private Long advertisementId;

    public ReportDTO() {}

    public ReportDTO(Long advertisementId, String comment) {
        this.advertisementId = advertisementId;
        this.comment = comment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAdvertisementId() {
        return advertisementId;
    }

    public void setAdvertisementId(Long advertisementId) {
        this.advertisementId = advertisementId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}