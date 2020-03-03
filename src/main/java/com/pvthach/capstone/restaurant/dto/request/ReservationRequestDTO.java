package com.pvthach.capstone.restaurant.dto.request;



import java.io.Serializable;

/**
 * Created by THACH-PC
 */

public class ReservationRequestDTO implements Serializable {

    private String from;

    private String to;

    private String reserveBy;

    private String email;

    private String phone;

    private Integer seat;

    public ReservationRequestDTO() {
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getReserveBy() {
        return reserveBy;
    }

    public void setReserveBy(String reserveBy) {
        this.reserveBy = reserveBy;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getSeat() {
        return seat;
    }

    public void setSeat(Integer seat) {
        this.seat = seat;
    }
}