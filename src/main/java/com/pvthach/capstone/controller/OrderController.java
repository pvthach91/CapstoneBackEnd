//package com.pvthach.capstone.controller;
//
////import com.pvthach.capstone.accounting.dto.request.ReceiptRequestDTO;
////import com.pvthach.capstone.accounting.model.ReceiptType;
//import com.pvthach.capstone.message.response.ApiResponse;
//import com.pvthach.capstone.message.response.Response;
//import com.pvthach.capstone.model.RoleName;
//import com.pvthach.capstone.dto.OrderDTO;
//import com.pvthach.capstone.dto.OrderSearchCriteria;
//import com.pvthach.capstone.dto.OrderType;
//import com.pvthach.capstone.dto.request.OrderItemRequestDTO;
//import com.pvthach.capstone.dto.request.OrderRequestDTO;
//import com.pvthach.capstone.model.*;
//import com.pvthach.capstone.farming.repository.DishRepository;
//import com.pvthach.capstone.farming.repository.OrderRepository;
//import com.pvthach.capstone.service.MailService;
//import com.pvthach.capstone.service.Page;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.*;
//
///**
// * Created by THACH-PC
// */
//
//@CrossOrigin(origins = "*", maxAge = 3600)
//@RestController
//public class OrderController {
//
//	@Autowired
//	OrderRepository orderRepository;
//
//	@Autowired
//	DishRepository dishRepository;
//
//	@Autowired
//	MailService mailService;
//
//	RestTemplate restTemplate = new RestTemplate();
//
//	@Value("${foodproducer.app.accounting}")
//	private String accountingHost;
//
//	@PostMapping("/api/orders")
//	@PreAuthorize("hasRole('SALE')")
//	public Page<List<OrderDTO>> getOrders(@RequestBody OrderSearchCriteria searchCriteria) {
//		return orderRepository.searchOrders(searchCriteria);
//	}
//
//	@GetMapping("/api/order/{orderId}")
//	@PreAuthorize("hasRole('SALE')")
//	public ApiResponse<OrderDTO> getDish(@PathVariable Long orderId) {
//		Optional<Ordering> orderDetail = orderRepository.findById(orderId);
//		if (!orderDetail.isPresent()) {
//			return Response.failedResult("Failed to get order");
//		}
//		OrderDTO orderDTO = orderDetail.get().convertToDTO();
//
//		return Response.successResult(orderDTO);
//	}
//
//
//	@PostMapping("/api/order/finish/{orderId}")
//	@PreAuthorize("hasRole('SALE')")
//	public ApiResponse<OrderDTO> finishOrder(@PathVariable Long orderId) {
//		Ordering order = orderRepository.findById(orderId).orElseThrow(
//				() -> new UsernameNotFoundException("Order is invalid"));
//
//		order.setStatus(OrderStaus.FINISHED.name());
//		Ordering saved = orderRepository.save(order);
//		OrderDTO dto = saved.convertToDTO();
//
////		ReceiptRequestDTO receipt = new ReceiptRequestDTO();
////		String name = SecurityContextHolder.getContext().getAuthentication().getName();
////		if (dto.getOrderType() != null && OrderType.SELLING_OFFLINE.name().equals(dto.getOrderType())) {
////			receipt.setReceiptType(ReceiptType.SELLING_OFFLINE.name());
////		} else {
////			receipt.setReceiptType(ReceiptType.SELLING_ONLINE.name());
////		}
////		receipt.setRef(dto.getOrderId());
////		receipt.setTotalPrice(dto.getTotalPrice().longValue());
////		receipt.setCreatedBy(name);
////		String url = accountingHost + "/api/receipt/create";
////		ApiResponse<String> result = restTemplate.postForObject(url, receipt, ApiResponse.class);
//
//		mailService.sendOrderFinished(saved);
//
//		return Response.successResult(dto);
//	}
//
//	@PostMapping("/api/order/process/{orderId}")
//	@PreAuthorize("hasRole('SALE')")
//	public ApiResponse<OrderDTO> processOrder(@PathVariable Long orderId) {
//		Ordering order = orderRepository.findById(orderId).orElseThrow(
//				() -> new UsernameNotFoundException("Order is invalid"));
//
//		order.setStatus(OrderStaus.PROCESSED.name());
//		Ordering saved = orderRepository.save(order);
//		OrderDTO dto = saved.convertToDTO();
//
//		mailService.sendOrderProcessed(saved);
//
//		return Response.successResult(dto);
//	}
//
//	@PostMapping("/api/order/cancel/{orderId}")
//	@PreAuthorize("hasRole('SALE')")
//	public ApiResponse<OrderDTO> cancelOrder(@PathVariable Long orderId) {
//		Ordering order = orderRepository.findById(orderId).orElseThrow(
//				() -> new UsernameNotFoundException("Order is invalid"));
//
//		order.setStatus(OrderStaus.CANCELLED.name());
//		Ordering saved = orderRepository.save(order);
//		OrderDTO dto = saved.convertToDTO();
//
//		mailService.sendOrderCancelled(saved);
//
//		return Response.successResult(dto);
//	}
//
//	@PostMapping("/api/order/deliver/{orderId}")
//	@PreAuthorize("hasRole('SALE')")
//	public ApiResponse<OrderDTO> deliverOrder(@PathVariable Long orderId) {
//		Ordering order = orderRepository.findById(orderId).orElseThrow(
//				() -> new UsernameNotFoundException("Order is invalid"));
//
//		order.setStatus(OrderStaus.DELIVERING.name());
//		Ordering saved = orderRepository.save(order);
//		OrderDTO dto = saved.convertToDTO();
//
//		mailService.sendOrderDelivering(saved);
//
//		return Response.successResult(dto);
//	}
//
//	@PostMapping("/api/order/delete/{orderId}")
//	@PreAuthorize("hasRole('SALE')")
//	public ApiResponse<String> deleteDish(@PathVariable Long orderId) {
//		orderRepository.deleteById(orderId);
//		return Response.successResult("Order has been deleted successfully");
//	}
//
//	@PostMapping("/api/guest/order/create")
//	public ApiResponse<String> creatOrder(@RequestBody OrderRequestDTO dto) {
////		if (dto == null || dto.getItems().size() == 0) {
////			return Response.failedResult("No dish selected");
////		}
////		Ordering order = new Ordering();
////
////		List<OrderItem> items = new ArrayList<OrderItem>();
////		for (OrderItemRequestDTO dtos : dto.getItems()) {
////			Dish dish = dishRepository.findById(dtos.getDishId()).orElseThrow(
////					() -> new UsernameNotFoundException("Dish not found"));
////			OrderItem orderItem = new OrderItem();
////			orderItem.setDish(dish);
////			orderItem.setQuantity(dtos.getQuantity());
////			orderItem.setOrdering(order);
////			items.add(orderItem);
////		}
////
////
////		order.setOrderBy(dto.getOrderBy());
////		order.setEmail(dto.getEmail());
////		order.setPhone(dto.getPhone());
////		order.setAddress(dto.getAddress());
////		order.setTotalPrice(dto.getTotalPrice());
////		order.setStatus(OrderStaus.PROCESSING.name());
////		order.setItems(items);
////		if (SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
////			List<GrantedAuthority> au = (List<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
////			String role = au.get(0).getAuthority();
////			if (RoleName.ROLE_SALE.name().equals(role)) {
////				order.setOrderType(OrderType.SELLING_OFFLINE.name());
////			} else {
////				order.setOrderType(OrderType.SELLING_ONLINE.name());
////			}
////		}
////
////		Ordering savedOrder = orderRepository.save(order);
////
////		mailService.sendOrderProcessing(savedOrder);
////		return Response.successResult(savedOrder.getOrderId());
//
//		return Response.successResult("");
//	}
//
//	@GetMapping("/api/guest/order/track/{orderId}")
//	public ApiResponse<OrderDTO> deliverReservation(@PathVariable String orderId) {
//		Optional<Ordering> order = orderRepository.findByOrderId(orderId);
//		if (!order.isPresent()) {
//			return Response.failedResult("Order not found");
//		}
//		Ordering o = order.get();
//		OrderDTO dto = o.convertToDTO();
//
//		return Response.successResult(dto);
//	}
//
//}
