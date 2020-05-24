package com.pvthach.capstone.controller;

import com.pvthach.capstone.dto.AddressDTO;
import com.pvthach.capstone.dto.OrderDTO;
import com.pvthach.capstone.dto.OrderSearchCriteria;
import com.pvthach.capstone.dto.request.OrderItemRequestDTO;
import com.pvthach.capstone.dto.request.OrderRequestDTO;
import com.pvthach.capstone.message.response.ApiResponse;
import com.pvthach.capstone.message.response.Response;
import com.pvthach.capstone.model.*;
import com.pvthach.capstone.repository.order.OrderRepository;
import com.pvthach.capstone.repository.product.ProductRepository;
import com.pvthach.capstone.repository.user.UserRepository;
import com.pvthach.capstone.service.MailService;
import com.pvthach.capstone.service.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
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
public class OrderController {

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	ProductRepository productRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	MailService mailService;


	// Get orders by buyer farmer and pm
	@PostMapping("/api/orders")
	@PreAuthorize("hasRole('FARMER') or hasRole('BUYER') or hasRole('PM')")
	public Page<List<OrderDTO>> getOrders(@RequestBody OrderSearchCriteria searchCriteria) {

		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userRepository.findByUsername(username).orElseThrow(
				() -> new UsernameNotFoundException("User not found"));
		if (SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
			List<GrantedAuthority> au = (List<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
			String role = au.get(0).getAuthority();
			if (RoleName.ROLE_BUYER.name().equals(role)) {
				searchCriteria.setOrderBy(username);
			} else if (RoleName.ROLE_FARMER.name().equals(role)) {
				searchCriteria.setFarmer(username);
			} else if (RoleName.ROLE_PM.name().equals(role)) {
				// Get all
			}
		}
		return orderRepository.searchOrders(searchCriteria);
	}

	@GetMapping("/api/order/{orderId}")
	@PreAuthorize("hasRole('FARMER') or hasRole('BUYER') or hasRole('PM')")
	public ApiResponse<OrderDTO> getDish(@PathVariable Long orderId) {
		Optional<Ordering> orderDetail = orderRepository.findById(orderId);
		if (!orderDetail.isPresent()) {
			return Response.failedResult("Failed to get order");
		}
		OrderDTO orderDTO = orderDetail.get().convertToDTO();

		return Response.successResult(orderDTO);
	}


	@PostMapping("/api/order/finish/{orderId}")
	@PreAuthorize("hasRole('PM')")
	public ApiResponse<OrderDTO> finishOrder(@PathVariable Long orderId) {
		Ordering order = orderRepository.findById(orderId).orElseThrow(
				() -> new UsernameNotFoundException("Order is invalid"));

		order.setStatus(OrderStaus.FINISHED.name());
		Ordering saved = orderRepository.save(order);
		OrderDTO dto = saved.convertToDTO();

		mailService.sendOrderFinished(saved);

		return Response.successResult(dto);
	}

	@PostMapping("/api/order/process/{orderId}")
	@PreAuthorize("hasRole('PM')")
	public ApiResponse<OrderDTO> processOrder(@PathVariable Long orderId) {
		Ordering order = orderRepository.findById(orderId).orElseThrow(
				() -> new UsernameNotFoundException("Order is invalid"));

		order.setStatus(OrderStaus.PROCESSED.name());
		Ordering saved = orderRepository.save(order);
		OrderDTO dto = saved.convertToDTO();

		mailService.sendOrderProcessed(saved);

		return Response.successResult(dto);
	}

	@PostMapping("/api/order/cancel/{orderId}")
	@PreAuthorize("hasRole('PM')")
	public ApiResponse<OrderDTO> cancelOrder(@PathVariable Long orderId) {
		Ordering order = orderRepository.findById(orderId).orElseThrow(
				() -> new UsernameNotFoundException("Order is invalid"));

		for (OrderItem item : order.getItems()) {
//			Product product = productRepository.findById(item.getProduct().getId()).orElseThrow(
//					() -> new UsernameNotFoundException("Product not found"));

			Product product = item.getProduct();

			product.setTotalOrder(product.getQuantity() - item.getQuantity());
			productRepository.save(product);
		}

		order.setStatus(OrderStaus.CANCELLED.name());
		Ordering saved = orderRepository.save(order);
		OrderDTO dto = saved.convertToDTO();

		mailService.sendOrderCancelled(saved);

		return Response.successResult(dto);
	}

	@PostMapping("/api/order/deliver/{orderId}")
	@PreAuthorize("hasRole('PM')")
	public ApiResponse<OrderDTO> deliverOrder(@PathVariable Long orderId) {
		Ordering order = orderRepository.findById(orderId).orElseThrow(
				() -> new UsernameNotFoundException("Order is invalid"));

		order.setStatus(OrderStaus.DELIVERING.name());
		Ordering saved = orderRepository.save(order);
		OrderDTO dto = saved.convertToDTO();

		mailService.sendOrderDelivering(saved);

		return Response.successResult(dto);
	}

	@PostMapping("/api/order/delete/{orderId}")
	@PreAuthorize("hasRole('PM')")
	public ApiResponse<String> deleteDish(@PathVariable Long orderId) {
		orderRepository.deleteById(orderId);
		return Response.successResult("Order has been deleted successfully");
	}

	@PostMapping("/api/order/create")
	@PreAuthorize("hasRole('BUYER')")
	public ApiResponse<String> creatOrder(@RequestBody OrderRequestDTO dto) {
		if (dto == null || dto.getItems().size() == 0) {
			return Response.failedResult("No Product selected");
		}
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userRepository.findByUsername(username).orElseThrow(
				() -> new UsernameNotFoundException("User not found"));
		Ordering order = new Ordering();

		List<OrderItem> items = new ArrayList<OrderItem>();
		for (OrderItemRequestDTO dtos : dto.getItems()) {
			Product product = productRepository.findById(dtos.getProductId()).orElseThrow(
					() -> new UsernameNotFoundException("Product not found"));
			OrderItem orderItem = new OrderItem();
			orderItem.setProduct(product);
			orderItem.setQuantity(dtos.getQuantity());
			orderItem.setPrice(product.getPromotionPrice());
			orderItem.setOrdering(order);
			items.add(orderItem);

			product.setTotalOrder(product.getQuantity() + dtos.getQuantity());
			productRepository.save(product);
		}


		order.setOrderBy(user);
		order.setAddress(dto.getAddress());
		order.setLatitude(dto.getLatitude());
		order.setLongitude(dto.getLongitude());
		order.setTotalPrice(dto.getTotalPrice());
		order.setStatus(OrderStaus.PROCESSING.name());
		order.setShippingMethod(dto.getShippingMethod());
		order.setShippingPrice(dto.getShippingPrice());
		order.setItems(items);

		Ordering savedOrder = orderRepository.save(order);

		mailService.sendOrderProcessing(savedOrder);
		return Response.successResult(savedOrder.getOrderId());
	}

	@GetMapping("/api/guest/order/track/{orderId}")
	public ApiResponse<OrderDTO> deliverReservation(@PathVariable String orderId) {
		Optional<Ordering> order = orderRepository.findByOrderId(orderId);
		if (!order.isPresent()) {
			return Response.failedResult("Order not found");
		}
		Ordering o = order.get();
		OrderDTO dto = o.convertToDTO();

		return Response.successResult(dto);
	}

}
