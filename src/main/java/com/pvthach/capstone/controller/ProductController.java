package com.pvthach.capstone.controller;

import com.pvthach.capstone.dto.ProductDTO;
import com.pvthach.capstone.dto.ProductSearchCriteria;
import com.pvthach.capstone.dto.VehicleDTO;
import com.pvthach.capstone.dto.response.ProductDetailDTO;
import com.pvthach.capstone.message.response.ApiResponse;
import com.pvthach.capstone.message.response.Response;
import com.pvthach.capstone.model.*;
import com.pvthach.capstone.repository.CommentRepository;
import com.pvthach.capstone.repository.RateRepository;
import com.pvthach.capstone.repository.VehicleRepository;
import com.pvthach.capstone.repository.product.ProductRepository;
import com.pvthach.capstone.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Created by THACH-PC
 */

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class ProductController {

	@Autowired
	ProductRepository productRepository;

	@Autowired
	CommentRepository commentRepository;

	@Autowired
	RateRepository rateRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	VehicleRepository vehicleRepository;

	@PostMapping("/api/guest/products")
	public List<ProductDTO> getProducts(@RequestBody ProductSearchCriteria criteriaSearch) {
		List<Product> products = productRepository.searchProducts(criteriaSearch);
		List<ProductDTO> result = new ArrayList<ProductDTO>();

		// Set vehicles
		List<Vehicle> vehicles = vehicleRepository.findAll();
		Map<Long, List<VehicleDTO>> vehicleMap = new HashMap<Long, List<VehicleDTO>>();
		for (Vehicle v: vehicles) {
			if (vehicleMap.containsKey(v.getUser().getId())) {
				vehicleMap.get(v.getUser().getId()).add(v.convertToDTO());
			} else {
				List<VehicleDTO> list = new ArrayList<VehicleDTO>();
				list.add(v.convertToDTO());
				vehicleMap.put(v.getUser().getId(), list);
			}
		}
		for (Product p : products) {
			ProductDTO dto = p.convertToDTO();
			List<VehicleDTO> list = vehicleMap.get(dto.getUser().getId());
			dto.getUser().setVehicles(list);

			result.add(dto);
		}

		// Recommendation will be applied here

		//sort, state
//			List<ProductDTO> result = Product.convertToDTOs(products);
		return result;
	}

	@GetMapping("/api/products")
	public List<ProductDTO> getProductsForFarmer() {

		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userRepository.findByUsername(username).orElseThrow(
				() -> new UsernameNotFoundException("User not found"));
		// Recommendation will be applied here

		List<Product> products = productRepository.findAllByUser(user);
		return Product.convertToDTOs(products);
	}

	@GetMapping("/api/guest/product/{id}")
	public ApiResponse<ProductDetailDTO> getProduct(@PathVariable Long id) {
		Optional<Product> productDetail = productRepository.findById(id);
		if (!productDetail.isPresent()) {
			return Response.failedResult("Failed to get product");
		}
		Product product = productDetail.get();
		List<Vehicle> vehicles = vehicleRepository.findAllByUser(product.getUser());

		// Recommendation will be applied here
		List<Product> recommendations = productRepository.findTop4By();

		List<Comment> commments = commentRepository.findAllByProductOrderByDateCreatedDesc(product);

		List<Rate> rates = rateRepository.findAllByProductOrderByDateCreatedDesc(product);

		ProductDetailDTO detail = new ProductDetailDTO();
		detail.setDto(product.convertToDTO());
		detail.getDto().getUser().setVehicles(Vehicle.convertToDTOs(vehicles));
		detail.setRecommendations(Product.convertToDTOs(recommendations));
		detail.setComments(Comment.convertToDTOs(commments));
		detail.setRates(Rate.convertToDTOs(rates));

		return Response.successResult(detail);
	}

	@PostMapping("/api/product/delete/{id}")
	@PreAuthorize("hasRole('FARMER')")
	public ApiResponse<String> deleteProduct(@PathVariable Long id) {
		productRepository.deleteById(id);
		return Response.successResult("Product has been deleted successfully");
	}

	@PostMapping("/api/product/add")
	@PreAuthorize("hasRole('FARMER')")
	public ApiResponse<ProductDTO> addProduct(@RequestBody ProductDTO dto) {
		Product product = new Product();

		if (dto.getId() != null) {
			product = productRepository.findById(dto.getId()).orElseThrow(
					() -> new UsernameNotFoundException("Product is invalid"));
		}
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userRepository.findByUsername(username).orElseThrow(
				() -> new UsernameNotFoundException("User not found"));

		product.setName(dto.getName());
		product.setCategory(dto.getCategory());
		product.setPrice(dto.getPrice());
		if (dto.getPromotionActive()) {
			if (dto.getPromotionPrice() >= dto.getPrice()) {
				return Response.failedResult("Promotion proce must be less than normal price");
			}
			product.setPromotionPrice(dto.getPromotionPrice());
		} else {
			product.setPromotionPrice(dto.getPrice());
		}
		product.setPromotionActive(dto.getPromotionActive());
		product.setDescription(dto.getDescription());
		String img = String.join(";", dto.getImages());
		product.setImages(img);
		product.setLatitude(dto.getLatitude());
		product.setLongitude(dto.getLongitude());
		product.setUser(user);
		product.setQuantity(dto.getQuantity());
		product.setStoreLocation(dto.getStoreLocation());
		product.setLocationRef(dto.getLocationRef());
		Product savedProduct = productRepository.save(product);

		return Response.successResult(savedProduct.convertToDTO());
	}
}
