package com.learning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.enums.Status;
import com.learning.model.OrderRequest;
import com.learning.model.OrderResponse;
import com.learning.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;

	@PostMapping
	public OrderResponse postOrder(@RequestBody OrderRequest orderRequest) {
		return orderService.createOrder(orderRequest);
	}
	
	@GetMapping("/{id}")
	public OrderResponse getOrderById(@PathVariable Long id) {
		return orderService.findOrderById(id);
	}
	
	@PutMapping("/{id}")
	public OrderResponse updateOrder(@PathVariable Long id ,@RequestBody OrderRequest orderRequest) {
		return orderService.updateOrder(id, orderRequest);
	}
	
	@DeleteMapping("/{id}")
	public Status deleteOrder(@PathVariable Long id) {
		return orderService.deleteOrder(id);
	}
	
}
