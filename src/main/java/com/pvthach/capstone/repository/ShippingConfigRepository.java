package com.pvthach.capstone.repository;

import com.pvthach.capstone.model.ShippingConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by THACH-PC
 */

@Repository
public interface ShippingConfigRepository extends JpaRepository<ShippingConfig, Long> {
}