package com.learning.service;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.entity.OrderEntity;
import com.learning.enums.Status;
import com.learning.model.OrderRequest;
import com.learning.model.OrderResponse;
import com.learning.repository.OrderRepository;
import com.learning.utility.Convertor;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private Convertor convertor;

	public OrderResponse createOrder(OrderRequest orderRequest) {
		OrderResponse orderResponse = null;
		if (Objects.nonNull(orderRequest)) {
			OrderEntity orderEntity = convertor.requestToEntity(orderRequest);
			orderEntity = orderRepository.save(orderEntity);
			orderResponse = convertor.entityToResponse(orderEntity);
		}
		return orderResponse;
	}

	public OrderResponse findOrderById(Long id) {
		OrderResponse orderResponse = null;
		Optional<OrderEntity> optionalEntity = orderRepository.findById(id);
		if (optionalEntity.isPresent()) {
			OrderEntity orderEntity = optionalEntity.get();
			orderResponse = convertor.entityToResponse(orderEntity);
			return orderResponse;
		}
		return null;
	}

	public OrderResponse updateOrder(Long id, OrderRequest orderRequest) {
		OrderResponse orderResponse = null;
		Optional<OrderEntity> optionalEntity = orderRepository.findById(id);
		if (optionalEntity.isPresent()) {
			OrderEntity orderEntity = optionalEntity.get();
			orderEntity = convertor.updateEntity(orderRequest, orderEntity);
			orderEntity = orderRepository.save(orderEntity);
			orderResponse = convertor.entityToResponse(orderEntity);
			return orderResponse;
		}
		return null;
	}

	public Status deleteOrder(Long id) {
		Optional<OrderEntity> optionalEntity = orderRepository.findById(id);
		if (Objects.nonNull(optionalEntity)) {
			orderRepository.deleteById(id);
			return Status.SUCCESS;
		}
		return Status.FAILED;
	}
}
