package com.pvthach.capstone.restaurant.controller;

import com.pvthach.capstone.message.response.ApiResponse;
import com.pvthach.capstone.message.response.Response;
import com.pvthach.capstone.restaurant.dto.DishDTO;
import com.pvthach.capstone.restaurant.dto.response.DetailDishDTO;
import com.pvthach.capstone.restaurant.model.Dish;
import com.pvthach.capstone.restaurant.repository.DishRepository;
import com.pvthach.capstone.service.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by THACH-PC
 */

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class DishController {

	@Autowired
	DishRepository dishRepository;

	@GetMapping("/api/guest/dishes/{currentPage}/{pageSize}")
	public Page<List<DishDTO>> getDishes(@PathVariable Integer currentPage, @PathVariable Integer pageSize) {
		return dishRepository.searchDishes(currentPage, pageSize);
	}

	@GetMapping("/api/guest/dish/{dishId}")
	public ApiResponse<DetailDishDTO> getDish(@PathVariable Long dishId) {
		Optional<Dish> detailDish = dishRepository.findById(dishId);
		if (!detailDish.isPresent()) {
			return Response.failedResult("Failed to get dish");
		}
		List<Dish> relatedDishes = dishRepository.findTop4By();
		DetailDishDTO detail = new DetailDishDTO();
		detail.setDto(detailDish.get().convertToDTO());
		detail.setRelatedDishes(convertToDTOs(relatedDishes));

		return Response.successResult(detail);
	}

	@PostMapping("/api/dish/delete/{dishId}")
	@PreAuthorize("hasRole('SALE')")
	public ApiResponse<String> deleteDish(@PathVariable Long dishId) {
		dishRepository.deleteById(dishId);
		return Response.successResult("Dish has been deleted successfully");
	}

	@PostMapping("/api/dish/create")
	@PreAuthorize("hasRole('SALE')")
	public Dish creatDish(@RequestBody DishDTO dto) {
		Dish dish = new Dish();

		if (dto.getId() != null) {
			dish = dishRepository.findById(dto.getId()).orElseThrow(
					() -> new UsernameNotFoundException("Dish is invalid"));
		}

		dish.setName(dto.getName());
		dish.setDescription(dto.getDescription());
		dish.setPrice(dto.getPrice());
		String img = String.join(";", dto.getImages());
		dish.setImages(img);
		Dish savedDish = dishRepository.save(dish);
		return savedDish;
	}

	private List<DishDTO> convertToDTOs(List<Dish> dishes) {
		List<DishDTO> result = new ArrayList<DishDTO>();
		for (Dish dish : dishes) {
			result.add(dish.convertToDTO());
		}

		return result;
	}
}
