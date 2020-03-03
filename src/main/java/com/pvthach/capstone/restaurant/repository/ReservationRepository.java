package com.pvthach.capstone.restaurant.repository;

import com.pvthach.capstone.restaurant.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Created by THACH-PC
 */

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long>, ReservationCustomRepository {

}