package com.pvthach.capstone.restaurant.controller;

import com.pvthach.capstone.message.response.ApiResponse;
import com.pvthach.capstone.message.response.Response;
import com.pvthach.capstone.restaurant.dto.ReservationDTO;
import com.pvthach.capstone.restaurant.dto.ReservationSearchCriteria;
import com.pvthach.capstone.restaurant.dto.request.ReservationRequestDTO;
import com.pvthach.capstone.restaurant.model.Reservation;
import com.pvthach.capstone.restaurant.model.ReservationStaus;
import com.pvthach.capstone.restaurant.model.Restaurant;
import com.pvthach.capstone.restaurant.repository.ReservationRepository;
import com.pvthach.capstone.restaurant.repository.RestaurantRepository;
import com.pvthach.capstone.service.MailService;
import com.pvthach.capstone.service.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Created by THACH-PC
 */

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class ReservationController {

	@Autowired
	ReservationRepository reservationRepository;

	@Autowired
	RestaurantRepository restaurantRepository;

	@Autowired
	MailService mailService;

	@PostMapping("/api/reservations")
	@PreAuthorize("hasRole('SALE')")
	public Page<List<ReservationDTO>> getReservations(@RequestBody ReservationSearchCriteria searchCriteria) {
		return reservationRepository.searchReservations(searchCriteria);
	}

	@GetMapping("/api/reservation/{reservationId}")
	@PreAuthorize("hasRole('SALE')")
	public ApiResponse<ReservationDTO> getDish(@PathVariable Long reservationId) {
		Optional<Reservation> reservationDetail = reservationRepository.findById(reservationId);
		if (!reservationDetail.isPresent()) {
			return Response.failedResult("Failed to get reservation");
		}
		ReservationDTO dto = reservationDetail.get().convertToDTO();

		return Response.successResult(dto);
	}

	@PostMapping("/api/reservation/finish/{reservationId}")
	@PreAuthorize("hasRole('SALE')")
	public ApiResponse<ReservationDTO> finishReservation(@PathVariable Long reservationId) {
		Reservation reservation = reservationRepository.findById(reservationId).orElseThrow(
				() -> new UsernameNotFoundException("Reservation is invalid"));

		reservation.setStatus(ReservationStaus.FINISHED.name());
		Reservation saved = reservationRepository.save(reservation);
		ReservationDTO dto = saved.convertToDTO();

		return Response.successResult(dto);
	}

	@PostMapping("/api/reservation/approve/{reservationId}")
	public ApiResponse<ReservationDTO> approveReservation(@PathVariable Long reservationId) {
		Reservation reservation = reservationRepository.findById(reservationId).orElseThrow(
				() -> new UsernameNotFoundException("Reservation is invalid"));

		reservation.setStatus(ReservationStaus.RESERVED.name());
		Reservation saved = reservationRepository.save(reservation);
		ReservationDTO dto = saved.convertToDTO();

		mailService.sendReserved(saved);

		return Response.successResult(dto);
	}

	@PostMapping("/api/reservation/cancel/{reservationId}")
	@PreAuthorize("hasRole('SALE')")
	public ApiResponse<ReservationDTO> cancelReservation(@PathVariable Long reservationId) {
		Reservation reservation = reservationRepository.findById(reservationId).orElseThrow(
				() -> new UsernameNotFoundException("Reservation is invalid"));

		reservation.setStatus(ReservationStaus.CANCELLED.name());
		Reservation saved = reservationRepository.save(reservation);
		ReservationDTO dto = saved.convertToDTO();

		mailService.sendReservationCancelled(saved);

		return Response.successResult(dto);
	}

	@PostMapping("/api/reservation/delete/{reservationId}")
	@PreAuthorize("hasRole('SALE')")
	public ApiResponse<String> deleteDish(@PathVariable Long reservationId) {
		reservationRepository.deleteById(reservationId);
		return Response.successResult("Reservation has been deleted successfully");
	}

	@PostMapping("/api/guest/reservation/create")
	public ApiResponse<String> creatDish(@RequestBody ReservationRequestDTO dto) throws ParseException {
		Restaurant restaurant = restaurantRepository.findByName("Food Producer").orElseThrow(()-> new UsernameNotFoundException("Cannot find restaurant"));
		Reservation reservation = new Reservation();
		String pattern = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		Date requestFrom = simpleDateFormat.parse(dto.getFrom());
		Date requestTo = simpleDateFormat.parse(dto.getTo());
		Calendar calendarFrom = Calendar.getInstance();
		calendarFrom.setTime(requestFrom);
		Calendar calendarTo = Calendar.getInstance();
		calendarTo.setTime(requestTo);
		int year = calendarFrom.get(Calendar.YEAR);
		int month = calendarTo.get(Calendar.MONTH) + 1;
		int day = calendarTo.get(Calendar.DAY_OF_MONTH);

		int hourFrom = calendarFrom.get(Calendar.HOUR_OF_DAY);
		int hourTo = calendarTo.get(Calendar.HOUR_OF_DAY);
		int totalSeat = 0;
		List<Reservation> reservations = reservationRepository.searchReservations(year, month, day);
		for (Reservation r : reservations) {
			Calendar calendarResFrom = Calendar.getInstance();
			calendarResFrom.setTime(r.getFrom());
			Calendar calendarResTo = Calendar.getInstance();
			calendarResTo.setTime(r.getTo());
			if (hourFrom == calendarResFrom.get(Calendar.HOUR_OF_DAY)) {
				totalSeat += r.getSeat();
			} else if (hourFrom > calendarResFrom.get(Calendar.HOUR_OF_DAY)) {
				if (hourFrom < calendarResTo.get(Calendar.HOUR_OF_DAY)) {
					totalSeat += r.getSeat();
				}
			} else if (hourFrom < calendarResFrom.get(Calendar.HOUR_OF_DAY)) {
				if (hourTo > calendarResFrom.get(Calendar.HOUR_OF_DAY)) {
					totalSeat += r.getSeat();
				}
			}
		}

		if (restaurant.getTotalSeat() < totalSeat + dto.getSeat()) {
			return Response.failedResult("The chairs are not available at this time");
		}

		reservation.setFrom(simpleDateFormat.parse(dto.getFrom()));
		reservation.setTo(simpleDateFormat.parse(dto.getTo()));
		reservation.setReserveBy(dto.getReserveBy());
		reservation.setEmail(dto.getEmail());
		reservation.setPhone(dto.getPhone());
		reservation.setSeat(dto.getSeat());
		reservation.setTotalPrice(null);
		reservation.setStatus(ReservationStaus.PROCESSING.name());

		Reservation savedReservation = reservationRepository.save(reservation);
//		ReservationDTO reservationDTO = savedReservation.convertToDTO();

		mailService.sendReservationProcessing(savedReservation);

		return Response.successResult(savedReservation.getReservationId());
	}

}
