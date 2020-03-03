package com.pvthach.foodproducer.hr.controller;

import com.pvthach.foodproducer.hr.dto.TimeSheetSearchCriteria;
import com.pvthach.foodproducer.hr.dto.TimesheetDTO;
import com.pvthach.foodproducer.hr.dto.request.TimesheetRequestDTO;
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
public class TimeSheetController {


	RestTemplate restTemplate = new RestTemplate();

	@Value("${foodproducer.app.hr}")
	private String humanResourceHost;

	@PostMapping("/api/timesheets")
	@PreAuthorize("hasRole('HR')")
	public Page<List<TimesheetDTO>> getLeavings(@RequestBody TimeSheetSearchCriteria searchCriteria) {
		String url = humanResourceHost + "/api/timesheets";
		Page<List<TimesheetDTO>> result = restTemplate.postForObject(url, searchCriteria, Page.class);

		return result;
	}

	@GetMapping("/api/timesheet/{id}")
	@PreAuthorize("hasRole('HR')")
	public ApiResponse<TimesheetDTO> getTimeSheet(@PathVariable Long id) {
		String url = humanResourceHost + "/api/timesheet/" + id;
		ApiResponse<TimesheetDTO> result = restTemplate.getForObject(url, ApiResponse.class);

		return result;
	}


	@PostMapping("/api/timesheet/delete/{id}")
	@PreAuthorize("hasRole('HR')")
	public ApiResponse<String> deleteTimeSheet(@PathVariable Long id) {
		String url = humanResourceHost + "/api/timesheet/delete/" + id;
		ApiResponse<String> result = restTemplate.postForObject(url, null, ApiResponse.class);

		return result;
	}

	@PostMapping("/api/timesheet/create")
	@PreAuthorize("hasRole('HR')")
	public ApiResponse<TimesheetDTO> createTimeSheet(@RequestBody TimesheetRequestDTO dto) throws ParseException {
		String url = humanResourceHost + "/api/timesheet/create";
		ApiResponse<TimesheetDTO> result = restTemplate.postForObject(url, dto, ApiResponse.class);

		return result;
	}

	@PostMapping("/api/timesheet/edit")
	@PreAuthorize("hasRole('HR')")
	public ApiResponse<TimesheetDTO> editTimeSheet(@RequestBody TimesheetRequestDTO dto) throws ParseException {
		String url = humanResourceHost + "/api/timesheet/edit";
		ApiResponse<TimesheetDTO> result = restTemplate.postForObject(url, dto, ApiResponse.class);

		return result;
	}

	@PostMapping("/api/timesheet/approve/{id}")
	@PreAuthorize("hasRole('HR')")
	public ApiResponse<TimesheetDTO> approveTimeSheet(@PathVariable Long id) {
		String url = humanResourceHost + "/api/timesheet/approve/" + id;
		ApiResponse<TimesheetDTO> result = restTemplate.postForObject(url, null, ApiResponse.class);

		return result;
	}

	@PostMapping("/api/timesheet/cancel/{id}")
	@PreAuthorize("hasRole('HR')")
	public ApiResponse<TimesheetDTO> cancelTimeSheet(@PathVariable Long id) {
		String url = humanResourceHost + "/api/timesheet/cancel/" + id;
		ApiResponse<TimesheetDTO> result = restTemplate.postForObject(url, null, ApiResponse.class);

		return result;
	}
}
