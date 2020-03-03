package com.pvthach.foodproducer.accounting.controller;

import com.pvthach.foodproducer.accounting.dto.PayslipDTO;
import com.pvthach.foodproducer.accounting.dto.PayslipSearchCriteria;
import com.pvthach.foodproducer.accounting.dto.request.PayslipRequestDTO;
import com.pvthach.foodproducer.message.response.ApiResponse;
import com.pvthach.foodproducer.service.Page;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.util.List;

/**
 * Created by THACH-PC
 */

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class PayslipController {

	RestTemplate restTemplate = new RestTemplate();

	@Value("${foodproducer.app.accounting}")
	private String accountingHost;

	@PostMapping("/api/payslips")
	@PreAuthorize("hasRole('ACCOUNTING')")
	public Page<List<PayslipDTO>> getPayslips(@RequestBody PayslipSearchCriteria searchCriteria) {
		String url = accountingHost + "/api/payslips";
		Page<List<PayslipDTO>> result = restTemplate.postForObject(url, searchCriteria, Page.class);

		return result;
	}

	@GetMapping("/api/payslip/{id}")
	@PreAuthorize("hasRole('ACCOUNTING')")
	public ApiResponse<PayslipDTO> getPayslip(@PathVariable Long id) {
		String url = accountingHost + "/api/payslip/" + id;
		ApiResponse<PayslipDTO> result = restTemplate.getForObject(url, ApiResponse.class);

		return result;
	}

	@PostMapping("/api/payslip/delete/{id}")
	@PreAuthorize("hasRole('ACCOUNTING')")
	public ApiResponse<String> deletePayslip(@PathVariable Long id) {
		String url = accountingHost + "/api/payslip/delete/" + id;
		ApiResponse<String> result = restTemplate.postForObject(url, null, ApiResponse.class);

		return result;
	}

	@PostMapping("/api/payslip/create")
	@PreAuthorize("hasRole('ACCOUNTING')")
	public ApiResponse<String> creatPayslip(@RequestBody PayslipRequestDTO dto) throws ParseException {
		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		dto.setCreatedBy(name);
		String url = accountingHost + "/api/payslip/create";
		ApiResponse<String> result = restTemplate.postForObject(url, dto, ApiResponse.class);

		return result;
	}
}
