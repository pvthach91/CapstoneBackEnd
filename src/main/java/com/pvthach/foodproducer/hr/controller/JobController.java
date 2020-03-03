package com.pvthach.foodproducer.hr.controller;

import com.pvthach.foodproducer.hr.dto.JobDTO;
import com.pvthach.foodproducer.hr.dto.request.JobRequestDTO;
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
public class JobController {

	RestTemplate restTemplate = new RestTemplate();

	@Value("${foodproducer.app.hr}")
	private String humanResourceHost;

	@GetMapping("/api/guest/jobs/{currentPage}/{pageSize}")
	public Page<List<JobDTO>> getJobs(@PathVariable Integer currentPage, @PathVariable Integer pageSize) {
		String url = humanResourceHost + "/api/guest/jobs/" + currentPage + "/" + pageSize;
		Page<List<JobDTO>> result = restTemplate.getForObject(url, Page.class);

		return result;
	}

	@GetMapping("/api/guest/job/{id}")
	public ApiResponse<JobDTO> getJob(@PathVariable Long id) {
		String url = humanResourceHost + "/api/guest/job/" + id;
		ApiResponse<JobDTO> result = restTemplate.getForObject(url, ApiResponse.class);

		return result;
	}

	@PostMapping("/api/job/delete/{id}")
	@PreAuthorize("hasRole('HR')")
	public ApiResponse<String> deleteJob(@PathVariable Long id) {
		String url = humanResourceHost + "/api/job/delete/" + id;
		ApiResponse<String> result = restTemplate.postForObject(url, null, ApiResponse.class);

		return result;
	}

	@PostMapping("/api/job/create")
	@PreAuthorize("hasRole('HR')")
	public ApiResponse<String> creatJob(@RequestBody JobRequestDTO dto) throws ParseException {
		String url = humanResourceHost + "/api/job/create";
		ApiResponse<String> result = restTemplate.postForObject(url, dto, ApiResponse.class);

		return result;
	}
}
