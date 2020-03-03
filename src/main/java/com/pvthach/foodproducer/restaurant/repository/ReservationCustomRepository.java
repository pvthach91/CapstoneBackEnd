package com.pvthach.foodproducer.restaurant.repository;

import com.pvthach.foodproducer.restaurant.dto.ReservationDTO;
import com.pvthach.foodproducer.restaurant.dto.ReservationSearchCriteria;
import com.pvthach.foodproducer.restaurant.model.Reservation;
import com.pvthach.foodproducer.service.Page;

import java.util.List;


/**
 * Created by THACH-PC
 */
public interface ReservationCustomRepository {

    Page<List<ReservationDTO>> searchReservations(ReservationSearchCriteria criteria);

    List<Reservation> searchReservations(int year, int month, int day);
}