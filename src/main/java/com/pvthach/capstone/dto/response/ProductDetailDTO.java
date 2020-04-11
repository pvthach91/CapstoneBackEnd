package com.pvthach.capstone.dto.response;


import com.pvthach.capstone.dto.CommentDTO;
import com.pvthach.capstone.dto.ProductDTO;
import com.pvthach.capstone.dto.RateDTO;

import java.io.Serializable;
import java.util.List;

/**
 * Created by THACH-PC
 */

public class ProductDetailDTO implements Serializable {

    private ProductDTO dto;

    private List<CommentDTO> comments;

    private List<RateDTO> rates;

    private List<ProductDTO> recommendations;

    public ProductDetailDTO() {
    }

    public ProductDTO getDto() {
        return dto;
    }

    public void setDto(ProductDTO dto) {
        this.dto = dto;
    }

    public List<CommentDTO> getComments() {
        return comments;
    }

    public void setComments(List<CommentDTO> comments) {
        this.comments = comments;
    }

    public List<RateDTO> getRates() {
        return rates;
    }

    public void setRates(List<RateDTO> rates) {
        this.rates = rates;
    }

    public List<ProductDTO> getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(List<ProductDTO> recommendations) {
        this.recommendations = recommendations;
    }
}