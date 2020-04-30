package com.pvthach.capstone.controller;

import com.pvthach.capstone.dto.AddressDTO;
import com.pvthach.capstone.message.response.ApiResponse;
import com.pvthach.capstone.message.response.Response;
import com.pvthach.capstone.model.Address;
import com.pvthach.capstone.model.User;
import com.pvthach.capstone.repository.AddressRepository;
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
public class AddressController {

	@Autowired
	AddressRepository addressRepository;

	@Autowired
	UserRepository userRepository;

	@GetMapping("/api/guest/addresses/{id}")
	public List<AddressDTO> getFarms(@PathVariable Long id) {
		User user = userRepository.findById(id).orElseThrow(
				() -> new UsernameNotFoundException("User not found"));

		List<Address> addresses = addressRepository.findAllByUser(user);
		return Address.convertToDTOs(addresses);
	}

	@GetMapping("/api/guest/addresses")
	@PreAuthorize("hasRole('FARMER') || hasRole('BUYER')")
	public List<AddressDTO> getAddressesForCurrentUser() {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userRepository.findByUsername(username).orElseThrow(
				() -> new UsernameNotFoundException("User not found"));

		List<Address> addresses = addressRepository.findAllByUser(user);
		return Address.convertToDTOs(addresses);
	}

	@GetMapping("/api/guest/address/{id}")
	public ApiResponse<AddressDTO> getFarm(@PathVariable Long id) {
		Address farm = addressRepository.getOne(id);
		AddressDTO dto = farm.convertToDTO();
		return Response.successResult(dto);
	}


	@PostMapping("/api/farmer/deleteAddress/{id}")
	@PreAuthorize("hasRole('FARMER') || hasRole('BUYER')")
	public ApiResponse<String> deleteFarm(@PathVariable Long id) {
		addressRepository.deleteById(id);
		return Response.successResult("Farm has been deleted successfully");
	}


	@PostMapping("/api/farmer/addAddress")
	@PreAuthorize("hasRole('FARMER') || hasRole('BUYER')")
	public ApiResponse<AddressDTO> addAddress(@RequestBody AddressDTO dto) {
		Address address = new Address();

		if (dto.getId() != null) {
			address = addressRepository.findById(dto.getId()).orElseThrow(
					() -> new UsernameNotFoundException("Address is invalid"));
		}
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userRepository.findByUsername(username).orElseThrow(
				() -> new UsernameNotFoundException("User not found"));

		address.setAddress(dto.getAddress());
		address.setLatitude(dto.getLatitude());
		address.setLongitude(dto.getLongitude());
		address.setState(dto.getState());
		address.setName(dto.getName());
		address.setUser(user);
		Address savedFarm = addressRepository.save(address);
		AddressDTO savedDTO = savedFarm.convertToDTO();
		return Response.successResult(savedDTO);
	}
}
