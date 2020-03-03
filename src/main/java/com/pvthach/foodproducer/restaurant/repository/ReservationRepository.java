package com.pvthach.foodproducer.restaurant.repository;

import com.pvthach.foodproducer.restaurant.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Created by THACH-PC
 */

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long>, ReservationCustomRepository {

}