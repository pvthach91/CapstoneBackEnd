package com.pvthach.foodproducer.accounting.controller;

import com.pvthach.foodproducer.accounting.dto.ReportDTO;
import com.pvthach.foodproducer.accounting.dto.ReportSearchCriteria;
import com.pvthach.foodproducer.message.response.ApiResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by THACH-PC
 */

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class ReportController {
	RestTemplate restTemplate = new RestTemplate();

	@Value("${foodproducer.app.accounting}")
	private String accountingHost;

	@PostMapping("/api/reports")
	@PreAuthorize("hasRole('ACCOUNTING')")
	public ApiResponse<ReportDTO> getReports(@RequestBody ReportSearchCriteria criteria) {
		String url = accountingHost + "/api/reports";
		ApiResponse<ReportDTO> result = restTemplate.postForObject(url, criteria, ApiResponse.class);

		return result;
	}
}
