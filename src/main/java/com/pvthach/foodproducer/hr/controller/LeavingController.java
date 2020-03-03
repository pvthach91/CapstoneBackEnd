package com.pvthach.foodproducer.hr.controller;

import com.pvthach.foodproducer.hr.dto.LeavingDTO;
import com.pvthach.foodproducer.hr.dto.LeavingSearchCriteria;
import com.pvthach.foodproducer.hr.dto.request.LeavingRequestDTO;
import com.pvthach.foodproducer.message.response.ApiResponse;
import com.pvthach.foodproducer.service.Page;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.util.List;

/**
 * Created by THACH-PC
 */

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class LeavingController {

	RestTemplate restTemplate = new RestTemplate();

	@Value("${foodproducer.app.hr}")
	private String humanResourceHost;

	@PostMapping("/api/leavings")
	@PreAuthorize("hasRole('HR')")
	public Page<List<LeavingDTO>> getLeavings(@RequestBody LeavingSearchCriteria searchCriteria) {
		String url = humanResourceHost + "/api/leavings";
		Page<List<LeavingDTO>> result = restTemplate.postForObject(url, searchCriteria, Page.class);

		return result;
	}

	@GetMapping("/api/leaving/{id}")
	@PreAuthorize("hasRole('HR')")
	public ApiResponse<LeavingDTO> getLeaving(@PathVariable Long id) {
		String url = humanResourceHost + "/api/leaving/" + id;
		ApiResponse<LeavingDTO> result = restTemplate.getForObject(url, ApiResponse.class);

		return result;

	}


	@PostMapping("/api/leaving/delete/{id}")
	@PreAuthorize("hasRole('HR')")
	public ApiResponse<String> deleteLeaving(@PathVariable Long id) {
		String url = humanResourceHost + "/api/leaving/delete/" + id;
		ApiResponse<String> result = restTemplate.postForObject(url, null, ApiResponse.class);

		return result;
	}

	@PostMapping("/api/leaving/create")
	@PreAuthorize("hasRole('HR')")
	public ApiResponse<LeavingDTO> createLeaving(@RequestBody LeavingRequestDTO dto) throws ParseException {
		String url = humanResourceHost + "/api/leaving/create";
		ApiResponse<LeavingDTO> result = restTemplate.postForObject(url, dto, ApiResponse.class);

		return result;
	}

	@PostMapping("/api/leaving/edit")
	@PreAuthorize("hasRole('HR')")
	public ApiResponse<LeavingDTO> editLeaving(@RequestBody LeavingRequestDTO dto) throws ParseException {
		String url = humanResourceHost + "/api/leaving/edit";
		ApiResponse<LeavingDTO> result = restTemplate.postForObject(url, dto, ApiResponse.class);

		return result;
	}

	@PostMapping("/api/leaving/approve/{id}")
	@PreAuthorize("hasRole('HR')")
	public ApiResponse<LeavingDTO> approveLeaving(@PathVariable Long id) {
		String url = humanResourceHost + "/api/leaving/approve/" + id;
		ApiResponse<LeavingDTO> result = restTemplate.postForObject(url, null, ApiResponse.class);

		return result;
	}

	@PostMapping("/api/leaving/cancel/{id}")
	@PreAuthorize("hasRole('HR')")
	public ApiResponse<LeavingDTO> cancelLeaving(@PathVariable Long id) {
		String url = humanResourceHost + "/api/leaving/cancel/" + id;
		ApiResponse<LeavingDTO> result = restTemplate.postForObject(url, null, ApiResponse.class);

		return result;
	}


}
