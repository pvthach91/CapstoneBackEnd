package com.pvthach.capstone.controller;

import com.pvthach.capstone.dto.FarmDTO;
import com.pvthach.capstone.dto.VehicleDTO;
import com.pvthach.capstone.message.response.ApiResponse;
import com.pvthach.capstone.message.response.Response;
import com.pvthach.capstone.model.Farm;
import com.pvthach.capstone.model.User;
import com.pvthach.capstone.model.Vehicle;
import com.pvthach.capstone.repository.FarmRepository;
import com.pvthach.capstone.repository.VehicleRepository;
import com.pvthach.capstone.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by THACH-PC
 */

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class FarmerController {

	@Autowired
	FarmRepository farmRepository;

	@Autowired
	VehicleRepository vehicleRepository;

	@Autowired
	UserRepository userRepository;

	@GetMapping("/api/guest/farms/{id}")
	public List<FarmDTO> getFarms(@PathVariable Long id) {
		User user = userRepository.findById(id).orElseThrow(
				() -> new UsernameNotFoundException("User not found"));

		List<Farm> farms = farmRepository.findAllByUser(user);
		return Farm.convertToDTOs(farms);
	}

	@GetMapping("/api/guest/farms")
	@PreAuthorize("hasRole('FARMER')")
	public List<FarmDTO> getFarmsForCurrentUser() {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userRepository.findByUsername(username).orElseThrow(
				() -> new UsernameNotFoundException("User not found"));

		List<Farm> farms = farmRepository.findAllByUser(user);
		return Farm.convertToDTOs(farms);
	}

	@GetMapping("/api/guest/farm/{id}")
	public ApiResponse<FarmDTO> getFarm(@PathVariable Long id) {
		Farm farm = farmRepository.getOne(id);
		FarmDTO dto = farm.convertToDTO();
		return Response.successResult(dto);
	}

	@GetMapping("/api/guest/vehicles/{id}")
	public List<VehicleDTO> getVehicles(@PathVariable Long id) {
		User user = userRepository.findById(id).orElseThrow(
				() -> new UsernameNotFoundException("User not found"));

		List<Vehicle> vehicles = vehicleRepository.findAllByUser(user);
		return Vehicle.convertToDTOs(vehicles);
	}

	@GetMapping("/api/guest/vehicles")
	public List<VehicleDTO> getVehiclesForCurrentUser() {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userRepository.findByUsername(username).orElseThrow(
				() -> new UsernameNotFoundException("User not found"));

		List<Vehicle> vehicles = vehicleRepository.findAllByUser(user);
		return Vehicle.convertToDTOs(vehicles);
	}

	@GetMapping("/api/guest/vehicle/{id}")
	public ApiResponse<VehicleDTO> getVehicle(@PathVariable Long id) {
		Vehicle vehicle = vehicleRepository.getOne(id);
		VehicleDTO dto = vehicle.convertToDTO();
		return Response.successResult(dto);
	}


	@PostMapping("/api/farmer/deleteFarm/{id}")
	@PreAuthorize("hasRole('FARMER')")
	public ApiResponse<String> deleteFarm(@PathVariable Long id) {
		farmRepository.deleteById(id);
		return Response.successResult("Farm has been deleted successfully");
	}

	@PostMapping("/api/farmer/deleteVehicle/{id}")
	@PreAuthorize("hasRole('FARMER')")
	public ApiResponse<String> deleteVehicle(@PathVariable Long id) {
		vehicleRepository.deleteById(id);
		return Response.successResult("Vehicle has been deleted successfully");
	}

	@PostMapping("/api/farmer/addFarm")
	@PreAuthorize("hasRole('FARMER')")
	public ApiResponse<FarmDTO> addFarm(@RequestBody FarmDTO dto) {
		Farm farm = new Farm();

		if (dto.getId() != null) {
			farm = farmRepository.findById(dto.getId()).orElseThrow(
					() -> new UsernameNotFoundException("Farm is invalid"));
		}
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userRepository.findByUsername(username).orElseThrow(
				() -> new UsernameNotFoundException("User not found"));

		farm.setAddress(dto.getAddress());
		String img = String.join(";", dto.getImages());
		farm.setImages(img);
		farm.setLatitude(dto.getLatitude());
		farm.setLongitude(dto.getLongitude());
		farm.setState(dto.getState());
		farm.setUser(user);
		Farm savedFarm = farmRepository.save(farm);
		FarmDTO savedDTO = savedFarm.convertToDTO();
		return Response.successResult(savedDTO);
	}

	@PostMapping("/api/farmer/changeFarmPhoto")
	@PreAuthorize("hasRole('FARMER')")
	public ApiResponse<FarmDTO> changeFarmPhoto(@RequestBody FarmDTO dto) {
		Farm farm = farmRepository.findById(dto.getId()).orElseThrow(
				() -> new UsernameNotFoundException("Farm is invalid"));

		farm.setAddress(dto.getAddress());
		String img = String.join(";", dto.getImages());
		farm.setImages(img);
		Farm savedFarm = farmRepository.save(farm);
		FarmDTO savedDTO = savedFarm.convertToDTO();
		return Response.successResult(savedDTO);
	}

	@PostMapping("/api/farmer/addVehicle")
	@PreAuthorize("hasRole('FARMER')")
	public Long addVehicle(@RequestBody VehicleDTO dto) {
		Vehicle vehicle = new Vehicle();

		if (dto.getId() != null) {
			vehicle = vehicleRepository.findById(dto.getId()).orElseThrow(
					() -> new UsernameNotFoundException("Vehicle is invalid"));
		}
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userRepository.findByUsername(username).orElseThrow(
				() -> new UsernameNotFoundException("User not found"));

		vehicle.setName(dto.getName());
		vehicle.setPhoto(dto.getPhoto());
		vehicle.setPricePerKm(dto.getPricePerKm());
		vehicle.setWeightCarry(dto.getWeightCarry());
		vehicle.setUser(user);
		Vehicle savedVehicle = vehicleRepository.save(vehicle);
		return savedVehicle.getId();
	}
}
