package com.pvthach.capstone.restaurant.repository;

import com.pvthach.capstone.restaurant.dto.ReservationDTO;
import com.pvthach.capstone.restaurant.dto.ReservationSearchCriteria;
import com.pvthach.capstone.restaurant.model.Reservation;
import com.pvthach.capstone.service.Page;

import java.util.List;


/**
 * Created by THACH-PC
 */
public interface ReservationCustomRepository {

    Page<List<ReservationDTO>> searchReservations(ReservationSearchCriteria criteria);

    List<Reservation> searchReservations(int year, int month, int day);
}