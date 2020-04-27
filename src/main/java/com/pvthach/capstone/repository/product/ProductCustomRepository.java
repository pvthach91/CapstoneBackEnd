package com.pvthach.capstone.repository.product;

import com.pvthach.capstone.dto.ProductDTO;
import com.pvthach.capstone.dto.ProductSearchCriteria;
import com.pvthach.capstone.model.Product;
import com.pvthach.capstone.service.Page;

import java.util.List;


/**
 * Created by THACH-PC
 */
public interface ProductCustomRepository {

    Page<List<ProductDTO>> searchProducts(Integer currentPage, Integer pageSize);

    List<Product> searchProducts(ProductSearchCriteria criteria);
}