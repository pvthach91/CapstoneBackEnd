package com.pvthach.capstone.controller;

import com.pvthach.capstone.dto.RateDTO;
import com.pvthach.capstone.model.Product;
import com.pvthach.capstone.model.Rate;
import com.pvthach.capstone.model.User;
import com.pvthach.capstone.repository.RateRepository;
import com.pvthach.capstone.repository.product.ProductRepository;
import com.pvthach.capstone.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by THACH-PC
 */

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class RateController {

	@Autowired
	UserRepository userRepository;

	@Autowired
	RateRepository rateRepository;

	@Autowired
	ProductRepository productRepository;

	@GetMapping("/api/guest/rates/{id}")
	public List<RateDTO> getRates(@PathVariable Long id) {
		Product product = productRepository.findById(id).orElseThrow(
				() -> new UsernameNotFoundException("Product not found"));

		List<Rate> rates = rateRepository.findAllByProductOrderByDateCreatedDesc(product);
		return Rate.convertToDTOs(rates);
	}

	@PostMapping("/api/rate/addRate/{productId}")
	@PreAuthorize("hasRole('FARMER') or hasRole('BUYER')")
	public List<RateDTO> addRate(@RequestBody RateDTO dto, @PathVariable Long productId) {
		Rate rate = new Rate();

		if (dto.getId() != null) {
			rate = rateRepository.findById(dto.getId()).orElseThrow(
					() -> new UsernameNotFoundException("Rate is invalid"));
		}
		Product product = productRepository.findById(productId).orElseThrow(
				() -> new UsernameNotFoundException("Product not found"));

		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userRepository.findByUsername(username).orElseThrow(
				() -> new UsernameNotFoundException("User not found"));

		rate.setStar(dto.getStar());
		rate.setRatedBy(user);
		rate.setProduct(product);
		Rate savedRate = rateRepository.save(rate);

		List<Rate> rates = rateRepository.findAllByProductOrderByDateCreatedDesc(product);
		return Rate.convertToDTOs(rates);
	}
}
