package com.pvthach.capstone.controller;

import com.pvthach.capstone.dto.ProductDTO;
import com.pvthach.capstone.dto.response.ProductDetailDTO;
import com.pvthach.capstone.message.response.ApiResponse;
import com.pvthach.capstone.message.response.Response;
import com.pvthach.capstone.model.Comment;
import com.pvthach.capstone.model.Product;
import com.pvthach.capstone.model.Rate;
import com.pvthach.capstone.repository.CommentRepository;
import com.pvthach.capstone.repository.RateRepository;
import com.pvthach.capstone.repository.product.ProductRepository;
import com.pvthach.capstone.repository.user.UserRepository;
import com.pvthach.capstone.service.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

	@GetMapping("/api/guest/products")
	public Page<List<ProductDTO>> getDishes(@PathVariable Integer currentPage, @PathVariable Integer pageSize) {
		// Recommendation will be applied here
		return productRepository.searchProducts(currentPage, pageSize);
	}

	@GetMapping("/api/guest/product/{id}")
	public ApiResponse<ProductDetailDTO> getDish(@PathVariable Long id) {
		Optional<Product> productDetail = productRepository.findById(id);
		if (!productDetail.isPresent()) {
			return Response.failedResult("Failed to get product");
		}
		Product product = productDetail.get();
		// Recommendation will be applied here
		List<Product> recommendations = productRepository.findTop4By();

		List<Comment> commments = commentRepository.findAllByProductOrderByDateCreatedDesc(product);

		List<Rate> rates = rateRepository.findAllByProductOrderByDateCreatedDesc(product);

		ProductDetailDTO detail = new ProductDetailDTO();
		detail.setDto(product.convertToDTO());
		detail.setRecommendations(Product.convertToDTOs(recommendations));
		detail.setComments(Comment.convertToDTOs(commments));
		detail.setRates(Rate.convertToDTOs(rates));

		return Response.successResult(detail);
	}

	@PostMapping("/api/product/delete/{id}")
	@PreAuthorize("hasRole('SALE')")
	public ApiResponse<String> deleteDish(@PathVariable Long id) {
		productRepository.deleteById(id);
		return Response.successResult("Product has been deleted successfully");
	}

	@PostMapping("/api/product/add")
	@PreAuthorize("hasRole('SALE')")
	public ProductDTO addProduct(@RequestBody ProductDTO dto) {
		Product product = new Product();

		if (dto.getId() != null) {
			product = productRepository.findById(dto.getId()).orElseThrow(
					() -> new UsernameNotFoundException("Product is invalid"));
		}

		product.setName(dto.getName());
		product.setCategory(dto.getCategory());
		product.setPrice(dto.getPrice());
		product.setPromotionPrice(dto.getPromotionPrice());
		product.setPromotionActive(dto.getPromotionActive());
		product.setDescription(dto.getDescription());
		String img = String.join(";", dto.getImages());
		product.setImages(img);
		product.setLatitude(dto.getLatitude());
		product.setLongitude(dto.getLongitude());
		Product savedProduct = productRepository.save(product);

		return savedProduct.convertToDTO();
	}
}
