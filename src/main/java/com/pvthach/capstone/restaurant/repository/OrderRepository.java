package com.pvthach.capstone.restaurant.repository;

import com.pvthach.capstone.restaurant.model.Ordering;
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