package com.pvthach.foodproducer.accounting.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pvthach.foodproducer.accounting.dto.*;
import com.pvthach.foodproducer.accounting.dto.request.PayrollRequestDTO;
import com.pvthach.foodproducer.hr.dto.TimesheetDTO;
import com.pvthach.foodproducer.hr.dto.TimesheetType;
import com.pvthach.foodproducer.message.response.ApiResponse;
import com.pvthach.foodproducer.message.response.Response;
import com.pvthach.foodproducer.service.Page;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by THACH-PC
 */

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class PayrollController {

	RestTemplate restTemplate = new RestTemplate();

	@Value("${foodproducer.app.accounting}")
	private String accountingHost;

	@Value("${foodproducer.app.hr}")
	private String humanResourceHost;

	@PostMapping("/api/payrolls")
	@PreAuthorize("hasRole('ACCOUNTING')")
	public Page<List<PayrollDTO>> getPayrolls(@RequestBody PayrollSearchCriteria searchCriteria) {
		String url = accountingHost + "/api/payrolls";
		Page<List<PayrollDTO>> result = restTemplate.postForObject(url, searchCriteria, Page.class);

		return result;
	}

	@GetMapping("/api/payroll/{id}")
	@PreAuthorize("hasRole('ACCOUNTING')")
	public ApiResponse<IntegratedPayrollDTO> getPayroll(@PathVariable Long id) {

		String url = accountingHost + "/api/payroll/" + id;
		ApiResponse<PayrollDTO> payrolls = restTemplate.getForObject(url, ApiResponse.class);

		if (!payrolls.isSuccess()) {
			return Response.failedResult(payrolls.getMessage());
		}

		ObjectMapper mapper= new ObjectMapper();
		PayrollDTO dto =mapper.convertValue(payrolls.getData(), PayrollDTO.class);
		List<String> timesheetRef = new ArrayList<String>();
		for (PayrollItemDTO item: dto.getItems()) {
			timesheetRef.add(item.getRef());
		}
		String humanResourceUrl = humanResourceHost + "/api/timesheetsForPayroll";
		List<TimesheetDTO> timesheetRaw = restTemplate.postForObject(humanResourceUrl, timesheetRef, List.class);
		List<TimesheetDTO> timesheets = mapper.convertValue(timesheetRaw, new TypeReference<List<TimesheetDTO>>(){});

		IntegratedPayrollDTO result = dto.integrate();

		List<IntegratedPayrollItemDTO> items = new ArrayList<>();
		for (int i = 0; i< dto.getItems().size(); i++) {
			PayrollItemDTO item = dto.getItems().get(i);
			IntegratedPayrollItemDTO integratedItem = item.integrate();
			integratedItem.setRef(timesheets.get(i));
			items.add(integratedItem);
		}

		result.setItems(items);
		result.setEmployee(timesheets.get(0).getEmployee());

		return Response.successResult(result);
	}

	@PostMapping("/api/payroll/delete/{id}")
	@PreAuthorize("hasRole('ACCOUNTING')")
	public ApiResponse<String> deletePayroll(@PathVariable Long id) {
		String url = accountingHost + "/api/payroll/delete/" + id;
		ApiResponse<String> result = restTemplate.postForObject(url, null, ApiResponse.class);

		return result;
	}

	@PostMapping("/api/payroll/create")
	@PreAuthorize("hasRole('ACCOUNTING')")
	public ApiResponse<String> creatPayroll(@RequestBody PayrollRequestDTO dto) throws ParseException {
		PayrollDTO payroll = new PayrollDTO();

		Long totalSalary = 0L;
		String hrUrl = humanResourceHost + "/api/timesheetsForCreatingPayroll/" +dto.getEmployeeId() + "/" + dto.getForYear() + "/" + dto.getForMonth();
		List timesheetRaw = restTemplate.getForObject(hrUrl, List.class);
		if (timesheetRaw == null || timesheetRaw.size() == 0) {
			return Response.failedResult("There's no timesheet for this employee this month");
		}
		ObjectMapper mapper= new ObjectMapper();
		List<TimesheetDTO> timesheetDTOs = mapper.convertValue(timesheetRaw, new TypeReference<List<TimesheetDTO>>(){});
		List<PayrollItemDTO> items = new ArrayList<PayrollItemDTO>();
		for (TimesheetDTO tdto: timesheetDTOs) {
			PayrollItemDTO payrollItem = new PayrollItemDTO();
			payrollItem.setRef(tdto.getTimesheetRef());
			int salaryPerDay = 0;
			if (tdto.getTimesheetType().equals(TimesheetType.WORKED.name()) || tdto.getTimesheetType().equals(TimesheetType.LEAVED_WITH_PAYING.name())) {
				salaryPerDay = tdto.getWorkingHour() * tdto.getEmployee().getSalaryRate();
			}
			payrollItem.setSalaryPerDay(salaryPerDay);
			items.add(payrollItem);
			totalSalary += salaryPerDay;

		}

		payroll.setForYear(dto.getForYear());
		payroll.setForMonth(dto.getForMonth());
		payroll.setTotalSalary(totalSalary);
		payroll.setEmployee(dto.getEmployeeId());
		payroll.setItems(items);

		String url = accountingHost + "/api/payroll/create";
		ApiResponse<String> result = restTemplate.postForObject(url, payroll, ApiResponse.class);

		return result;
	}
}
