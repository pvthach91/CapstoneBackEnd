package com.pvthach.foodproducer.stock.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pvthach.foodproducer.accounting.dto.request.PayslipRequestDTO;
import com.pvthach.foodproducer.accounting.model.PayslipType;
import com.pvthach.foodproducer.message.response.ApiResponse;
import com.pvthach.foodproducer.message.response.Response;
import com.pvthach.foodproducer.service.Page;
import com.pvthach.foodproducer.stock.dto.StockMovingDTO;
import com.pvthach.foodproducer.stock.dto.StockMovingSearchCriteria;
import com.pvthach.foodproducer.stock.dto.request.StockMovingRequestDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Created by THACH-PC
 */

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class StockMovingController {

	RestTemplate restTemplate = new RestTemplate();

	@Value("${foodproducer.app.stock}")
	private String stockManagementHost;

	@Value("${foodproducer.app.accounting}")
	private String accountingHost;

	@PostMapping("/api/stockReceives")
	@PreAuthorize("hasRole('STOCK') or hasRole('ACCOUNTING')")
	public Page<List<StockMovingDTO>> getStockReceives(@RequestBody StockMovingSearchCriteria searchCriteria) {
		String url = stockManagementHost + "/api/stockReceives";
		Page<List<StockMovingDTO>> result = restTemplate.postForObject(url, searchCriteria, Page.class);

		return result;
	}

	@PostMapping("/api/stockDelivers")
	@PreAuthorize("hasRole('STOCK')")
	public Page<List<StockMovingDTO>> getStockDelivers(@RequestBody StockMovingSearchCriteria searchCriteria) {
		String url = stockManagementHost + "/api/stockDelivers";
		Page<List<StockMovingDTO>> result = restTemplate.postForObject(url, searchCriteria, Page.class);

		return result;
	}

	@GetMapping("/api/stock/{id}")
	@PreAuthorize("hasRole('STOCK') or hasRole('ACCOUNTING')")
	public ApiResponse<StockMovingDTO> getStock(@PathVariable Long id) {
		String url = stockManagementHost + "/api/stock/" + id;
		ApiResponse<StockMovingDTO> result = restTemplate.getForObject(url, ApiResponse.class);

		return result;
	}

	@PostMapping("/api/stock/approveInAccounting/{id}")
	@PreAuthorize("hasRole('ACCOUNTING')")
	public ApiResponse<StockMovingDTO> approveStockFromAccounting(@PathVariable Long id) {
		String url = stockManagementHost + "/api/stock/approveInAccounting/" + id;
		ApiResponse<StockMovingDTO> result = restTemplate.postForObject(url, null, ApiResponse.class);

		return result;
	}


	@PostMapping("/api/stock/approveInStock/{id}")
	@PreAuthorize("hasRole('STOCK')")
	public ApiResponse<StockMovingDTO> approveStock(@PathVariable Long id) {
		String url = stockManagementHost + "/api/stock/approveInStock/" + id;
		ApiResponse<StockMovingDTO> result = restTemplate.postForObject(url, null, ApiResponse.class);
		if (!result.isSuccess()) {
			return Response.failedResult(result.getMessage());
		}
		ObjectMapper mapper = new ObjectMapper();
		StockMovingDTO stockMovingDTO = mapper.convertValue(result.getData(), StockMovingDTO.class);

		if (result.isSuccess()) {
			// Create Payslip in Accounting
			String name = SecurityContextHolder.getContext().getAuthentication().getName();
			PayslipRequestDTO dto = new PayslipRequestDTO();
			dto.setPayslipType(PayslipType.BUYING_INGREDIENT.name());
			dto.setRef(stockMovingDTO.getStockRef());
			dto.setTotalPrice(stockMovingDTO.getTotalPrice().longValue());
			dto.setCreatedBy(name);
			String accountingUrl = accountingHost + "/api/payslip/create";
			ApiResponse<String> createResult = restTemplate.postForObject(accountingUrl, dto, ApiResponse.class);
			if (!createResult.isSuccess()) {
				return Response.failedResult(createResult.getMessage());
			}
		}
		return result;
	}


	@PostMapping("/api/stock/cancel/{id}")
	@PreAuthorize("hasRole('STOCK')")
	public ApiResponse<StockMovingDTO> cancelStock(@PathVariable Long id) {
		String url = stockManagementHost + "/api/stock/cancel/" + id;
		ApiResponse<StockMovingDTO> result = restTemplate.postForObject(url, null, ApiResponse.class);

		return result;
	}


	@PostMapping("/api/stock/delete/{id}")
	@PreAuthorize("hasRole('STOCK')")
	public ApiResponse<String> deleteDish(@PathVariable Long id) {
		String url = stockManagementHost + "/api/stock/delete/" + id;
		ApiResponse<String> result = restTemplate.postForObject(url, null, ApiResponse.class);

		return result;
	}

	@PostMapping("/api/stock/createStockReceive")
		@PreAuthorize("hasRole('STOCK')")
	public ApiResponse<String> creatStockReceive(@RequestBody StockMovingRequestDTO dto) {
		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		dto.setCreatedBy(name);
		String url = stockManagementHost + "/api/stock/createStockReceive";
		ApiResponse<String> result = restTemplate.postForObject(url, dto, ApiResponse.class);

		return result;
	}

	@PostMapping("/api/stock/createStockDeliver")
		@PreAuthorize("hasRole('STOCK')")
	public ApiResponse<String> creatStockDeliver(@RequestBody StockMovingRequestDTO dto) {
		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		dto.setCreatedBy(name);
		String url = stockManagementHost + "/api/stock/createStockDeliver";
		ApiResponse<String> result = restTemplate.postForObject(url, dto, ApiResponse.class);

		return result;
	}

	@GetMapping("/api/stock/stockRef/{stockRef}")
	public ApiResponse<StockMovingDTO> getByStockRef(@PathVariable String stockRef) {
		String url = stockManagementHost + "/api/stock/stockRef/" + stockRef;
		ApiResponse<StockMovingDTO> result = restTemplate.getForObject(url, ApiResponse.class);

		return result;
	}

}
