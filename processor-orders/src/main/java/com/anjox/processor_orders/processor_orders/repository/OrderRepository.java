package com.anjox.processor_orders.processor_orders.repository;

import com.anjox.processor_orders.processor_orders.model.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<OrderModel, UUID> {
}
