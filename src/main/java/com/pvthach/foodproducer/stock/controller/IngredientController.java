package com.pvthach.foodproducer.stock.controller;

import com.pvthach.foodproducer.message.response.ApiResponse;
import com.pvthach.foodproducer.service.Page;
import com.pvthach.foodproducer.stock.dto.IngredientDTO;
import com.pvthach.foodproducer.stock.dto.IngredientSearchCriteria;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Created by THACH-PC
 */

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class IngredientController {

	RestTemplate restTemplate = new RestTemplate();

	@Value("${foodproducer.app.stock}")
	private String stockManagementHost;

	@PostMapping("/api/ingredients")
	@PreAuthorize("hasRole('STOCK')")
	public Page<List<IngredientDTO>> getIngredients(@RequestBody  IngredientSearchCriteria searchCriteria) {
		String url = stockManagementHost + "/api/ingredients";
		Page<List<IngredientDTO>> result = restTemplate.postForObject(url, searchCriteria, Page.class);

		return result;
	}

	@GetMapping("/api/allIngredients")
	@PreAuthorize("hasRole('STOCK')")
	public List<IngredientDTO> getAllIngredients() {
		String url = stockManagementHost + "/api/allIngredients";
		List<IngredientDTO> result = restTemplate.getForObject(url, List.class);

		return result;
	}


	@PostMapping("/api/ingredient/delete/{id}")
	@PreAuthorize("hasRole('STOCK')")
	public ApiResponse<String> deleteDish(@PathVariable Long id) {
		String url = stockManagementHost + "/api/ingredient/delete/" + id;
		ApiResponse<String> result = restTemplate.postForObject(url, null, ApiResponse.class);

		return result;
	}

	@PostMapping("/api/ingredient/create")
	@PreAuthorize("hasRole('STOCK')")
	public ApiResponse<String> creatDish(@RequestBody IngredientDTO dto) {
		String url = stockManagementHost + "/api/ingredient/create";
		ApiResponse<String> result = restTemplate.postForObject(url, dto, ApiResponse.class);

		return result;
	}
}
