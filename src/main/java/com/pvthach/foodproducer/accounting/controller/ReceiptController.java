package com.pvthach.foodproducer.accounting.controller;

import com.pvthach.foodproducer.accounting.dto.ReceiptDTO;
import com.pvthach.foodproducer.accounting.dto.ReceiptSearchCriteria;
import com.pvthach.foodproducer.accounting.dto.request.ReceiptRequestDTO;
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
public class ReceiptController {

	RestTemplate restTemplate = new RestTemplate();

	@Value("${foodproducer.app.accounting}")
	private String accountingHost;

	@PostMapping("/api/receipts")
	@PreAuthorize("hasRole('ACCOUNTING')")
	public Page<List<ReceiptDTO>> getReceipts(@RequestBody ReceiptSearchCriteria searchCriteria) {
		String url = accountingHost + "/api/receipts";
		Page<List<ReceiptDTO>> result = restTemplate.postForObject(url, searchCriteria, Page.class);

		return result;
	}

	@GetMapping("/api/receipt/{id}")
	@PreAuthorize("hasRole('ACCOUNTING')")
	public ApiResponse<ReceiptDTO> getReceipt(@PathVariable Long id) {
		String url = accountingHost + "/api/receipt/" + id;
		ApiResponse<ReceiptDTO> result = restTemplate.getForObject(url, ApiResponse.class);

		return result;
	}

	@PostMapping("/api/receipt/delete/{id}")
	@PreAuthorize("hasRole('ACCOUNTING')")
	public ApiResponse<String> deleteReceipt(@PathVariable Long id) {
		String url = accountingHost + "/api/receipt/delete/" + id;
		ApiResponse<String> result = restTemplate.postForObject(url, null, ApiResponse.class);

		return result;
	}

	@PostMapping("/api/receipt/create")
	@PreAuthorize("hasRole('ACCOUNTING')")
	public ApiResponse<String> creatReceipt(@RequestBody ReceiptRequestDTO dto) throws ParseException {
		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		dto.setCreatedBy(name);
		String url = accountingHost + "/api/receipt/create";
		ApiResponse<String> result = restTemplate.postForObject(url, dto, ApiResponse.class);

		return result;
	}
}
