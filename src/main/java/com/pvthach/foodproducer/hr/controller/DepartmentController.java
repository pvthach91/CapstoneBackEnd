package com.pvthach.foodproducer.hr.controller;

import com.pvthach.foodproducer.hr.dto.DepartmentDTO;
import com.pvthach.foodproducer.hr.dto.DepartmentWithEmployeeDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Created by THACH-PC
 */

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class DepartmentController {

	RestTemplate restTemplate = new RestTemplate();

	@Value("${foodproducer.app.hr}")
	private String humanResourceHost;

	@GetMapping("/api/departments")
	public List<DepartmentDTO> getDepartments() {
		String url = humanResourceHost + "/api/departments";
		List<DepartmentDTO> result = restTemplate.getForObject(url, List.class);

		return result;
	}

	@GetMapping("/api/departmentsWithEmployees")
	public List<DepartmentWithEmployeeDTO> getDepartmentsWithEmployees() {
		String url = humanResourceHost + "/api/departmentsWithEmployees";
		List<DepartmentWithEmployeeDTO> result = restTemplate.getForObject(url, List.class);

		return result;
	}
}