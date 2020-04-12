package com.pvthach.capstone.model;



import com.fasterxml.jackson.annotation.JsonFormat;
import com.pvthach.capstone.dto.RateDTO;
import com.pvthach.capstone.ultil.DateFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by THACH-PC
 */

@Table(name = "RATE")
@Entity(name = "Rate")
public class Rate implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "STAR", nullable = false)
    private Integer star;

    @Column(name = "DATE_CREATED")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dateCreated;

    @OneToOne
    @JoinColumn(name = "RATED_BY")
    private User ratedBy;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="PRODUCT_ID")
    private Product product;

    public Rate() {
        this.dateCreated = new Date();
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

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public User getRatedBy() {
        return ratedBy;
    }

    public void setRatedBy(User ratedBy) {
        this.ratedBy = ratedBy;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public RateDTO convertToDTO() {
        RateDTO dto = new RateDTO();
        dto.setId(id);
        dto.setStar(star);
        dto.setDateCreated(DateFormat.format(dateCreated));
        dto.setRatedBy(ratedBy.convertToDTO());

        return dto;
    }

    public static List<RateDTO> convertToDTOs(List<Rate> list) {
        List<RateDTO> result = new ArrayList<RateDTO>();
        for (Rate c : list) {
            result.add(c.convertToDTO());
        }

        return result;
    }
}