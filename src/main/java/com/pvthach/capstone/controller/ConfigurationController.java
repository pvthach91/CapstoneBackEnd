package com.pvthach.capstone.controller;

import com.pvthach.capstone.dto.AddressDTO;
import com.pvthach.capstone.dto.ConfigurationDTO;
import com.pvthach.capstone.model.*;
import com.pvthach.capstone.repository.AddressRepository;
import com.pvthach.capstone.repository.shippingConfig.ShippingConfigRepository;
import com.pvthach.capstone.repository.StateRepository;
import com.pvthach.capstone.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by THACH-PC
 */

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class ConfigurationController {

	@Autowired
	ShippingConfigRepository shippingConfigRepository;

	@Autowired
	StateRepository stateRepository;

	@Autowired
	AddressRepository addressRepository;

	@Autowired
	UserRepository userRepository;


	@GetMapping("/api/configuration")
	@PreAuthorize("hasRole('FARMER') || hasRole('BUYER') || hasRole('PM')")
	public ConfigurationDTO getConfiguration() {
		ConfigurationDTO dto = new ConfigurationDTO();

		// State
		List<State> states = stateRepository.findAll();
		Collections.sort(states, new Comparator<State>() {
			@Override
			public int compare(State o1, State o2) {
				return o1.getId().compareTo(o2.getId());
			}
		});
		dto.setStates(states);

		// Shipping Config
		List<ShippingConfig> shippingConfigs = shippingConfigRepository.findAll();
		dto.setShippingConfigs(ShippingConfig.convertToDTOs(shippingConfigs));


		// Deliver address
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userRepository.findByUsername(username).orElseThrow(
				() -> new UsernameNotFoundException("User not found"));
		if (SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
			List<GrantedAuthority> au = (List<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
			String role = au.get(0).getAuthority();
			if (RoleName.ROLE_BUYER.name().equals(role)) {
				List<Address> deliveryAddresses = addressRepository.findAllByUser(user);
				dto.setDeliveryAddresses(Address.convertToDTOs(deliveryAddresses));
			} else {
				List<AddressDTO> list = new ArrayList<AddressDTO>();
				dto.setDeliveryAddresses(list);
			}
		}

		// Current user
		dto.setUser(user.convertToDTO());


		return dto;
	}
}
