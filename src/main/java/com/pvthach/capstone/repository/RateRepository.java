package com.pvthach.capstone.repository;

import com.pvthach.capstone.model.Product;
import com.pvthach.capstone.model.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by THACH-PC
 */

@Repository
public interface RateRepository extends JpaRepository<Rate, Long> {

    List<Rate> findAllByProductOrderByDateCreatedDesc(Product product);
}