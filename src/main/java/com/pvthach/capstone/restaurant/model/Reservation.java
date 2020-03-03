package com.pvthach.capstone.restaurant.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.pvthach.capstone.restaurant.dto.ReservationDTO;

import javax.persistence.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by THACH-PC
 */

@Table(name = "RESERVATION")
@Entity(name = "Reservation")
public class Reservation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "RESERVATION_ID", nullable = false)
    private String reservationId;

    @Column(name = "FROM_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date from;

    @Column(name = "TO_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date to;

    @Column(name = "RESERVE_BY", nullable = false)
    private String reserveBy;

    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Column(name = "PHONE", nullable = false)
    private String phone;

    @Column(name = "SEAT", nullable = false)
    private Integer seat;

    @Column(name = "TOTAL_PRICE")
    private Integer totalPrice;

    @Column(name = "STATUS", nullable = false)
    private String status;

    public Reservation() {
        this.reservationId = generateUniqueReservationId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReservationId() {
        return reservationId;
    }

    public void setReservationId(String reservationId) {
        this.reservationId = reservationId;
    }

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
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

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ReservationDTO convertToDTO() {
        String pattern = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        ReservationDTO dto = new ReservationDTO();
        dto.setId(id);
        dto.setReservationId(reservationId);
        dto.setFrom(sdf.format(from));
        dto.setTo(sdf.format(to));
        dto.setReserveBy(reserveBy);
        dto.setEmail(email);
        dto.setPhone(phone);
        dto.setSeat(seat);
        dto.setTotalPrice(totalPrice);
        dto.setStatus(status);

        return dto;
    }

    private String generateUniqueReservationId() {
        String pattern = "yyyyMMddHHmmss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String id = simpleDateFormat.format(new Date());

        return id;
    }
}