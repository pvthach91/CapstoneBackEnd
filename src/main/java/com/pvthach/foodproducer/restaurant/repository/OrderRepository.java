package com.pvthach.foodproducer.restaurant.repository;

import com.pvthach.foodproducer.restaurant.model.Ordering;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


/**
 * Created by THACH-PC
 */

@Repository
public interface OrderRepository extends JpaRepository<Ordering, Long>, OrderCustomRepository {

    Optional<Ordering> findByOrderId(String orderId);

}