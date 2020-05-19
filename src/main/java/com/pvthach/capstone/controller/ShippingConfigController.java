package com.pvthach.capstone.controller;

import com.pvthach.capstone.dto.ShippingConfigDTO;
import com.pvthach.capstone.dto.ShippingConfigSearchCriteria;
import com.pvthach.capstone.message.response.ApiResponse;
import com.pvthach.capstone.message.response.Response;
import com.pvthach.capstone.model.ShippingConfig;
import com.pvthach.capstone.repository.shippingConfig.ShippingConfigRepository;
import com.pvthach.capstone.service.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by THACH-PC
 */

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class ShippingConfigController {

	@Autowired
	ShippingConfigRepository shippingConfigRepository;


	@PostMapping("/api/shippingConfigs")
	@PreAuthorize("hasRole('FARMER') || hasRole('BUYER') || hasRole('PM')")
	public Page<List<ShippingConfigDTO>> getShippingConfigs(@RequestBody ShippingConfigSearchCriteria searchCriteria) {

		return shippingConfigRepository.searchShippingConfigs(searchCriteria);
	}

	@GetMapping("/api/shippingConfig/{id}")
	@PreAuthorize("hasRole('FARMER') || hasRole('BUYER') || hasRole('PM')")
	public ApiResponse<ShippingConfigDTO> getShippingConfig(@PathVariable Long id) {
		ShippingConfig config = shippingConfigRepository.getOne(id);
		ShippingConfigDTO dto = config.convertToDTO();
		return Response.successResult(dto);
	}



	@PostMapping("/api/deleteShippingConfig/{id}")
	@PreAuthorize("hasRole('PM')")
	public ApiResponse<String> deleteShippingConfig(@PathVariable Long id) {
		shippingConfigRepository.deleteById(id);
		return Response.successResult("Shipping config has been deleted successfully");
	}


	@PostMapping("/api/addShippingConfig")
	@PreAuthorize("hasRole('PM')")
	public Long addShippingConfig(@RequestBody ShippingConfigDTO dto) {
		ShippingConfig config = new ShippingConfig();

		if (dto.getId() != null) {
			config = shippingConfigRepository.findById(dto.getId()).orElseThrow(
					() -> new UsernameNotFoundException("Shipping config is invalid"));
		}

		config.setState(dto.getState());
		config.setPrice(dto.getPrice());
		config.setWeightCarryFrom(dto.getWeightCarryFrom());
		config.setWeightCarryTo(dto.getWeightCarryTo());
		ShippingConfig savedVehicle = shippingConfigRepository.save(config);
		return savedVehicle.getId();
	}
}
