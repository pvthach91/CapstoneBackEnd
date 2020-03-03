package com.pvthach.foodproducer.hr.controller;

import com.pvthach.foodproducer.hr.dto.EmployeeDTO;
import com.pvthach.foodproducer.hr.dto.EmployeeSearchCriteria;
import com.pvthach.foodproducer.hr.dto.request.EmployeeRequestDTO;
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
public class EmployeeController {

	RestTemplate restTemplate = new RestTemplate();

	@Value("${foodproducer.app.hr}")
	private String humanResourceHost;

	@PostMapping("/api/employees")
	@PreAuthorize("hasRole('HR')")
	public Page<List<EmployeeDTO>> getEmployees(@RequestBody EmployeeSearchCriteria searchCriteria) {
		String url = humanResourceHost + "/api/employees";
		Page<List<EmployeeDTO>> result = restTemplate.postForObject(url, searchCriteria, Page.class);

		return result;
	}

	@GetMapping("/api/allEmployees")
	@PreAuthorize("hasRole('HR')")
	public List<EmployeeDTO> getAllEmployees() {

		String url = humanResourceHost + "/api/allEmployees";
		List<EmployeeDTO> result = restTemplate.getForObject(url, List.class);

		return result;
	}

	@GetMapping("/api/employee/{id}")
	@PreAuthorize("hasRole('HR')")
	public ApiResponse<EmployeeDTO> getEmployee(@PathVariable Long id) {
		String url = humanResourceHost + "/api/employee/" + id;
		ApiResponse<EmployeeDTO> result = restTemplate.getForObject(url, ApiResponse.class);

		return result;
	}


	@PostMapping("/api/employee/delete/{id}")
	@PreAuthorize("hasRole('HR')")
	public ApiResponse<String> deleteEmployee(@PathVariable Long id) {
		String url = humanResourceHost + "/api/employee/delete/" + id;
		ApiResponse<String> result = restTemplate.postForObject(url, null, ApiResponse.class);

		return result;
	}

	@PostMapping("/api/employee/create")
	@PreAuthorize("hasRole('HR')")
	public ApiResponse<EmployeeDTO> createEmployee(@RequestBody EmployeeRequestDTO dto) throws ParseException {
		String url = humanResourceHost + "/api/employee/create";
		ApiResponse<EmployeeDTO> result = restTemplate.postForObject(url, dto, ApiResponse.class);

		return result;
	}

	@PostMapping("/api/employee/edit")
	@PreAuthorize("hasRole('HR')")
	public ApiResponse<EmployeeDTO> editEmployee(@RequestBody EmployeeRequestDTO dto) throws ParseException {
		String url = humanResourceHost + "/api/employee/edit";
		ApiResponse<EmployeeDTO> result = restTemplate.postForObject(url, dto, ApiResponse.class);

		return result;
	}
}
