package com.learning.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.entity.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

}
