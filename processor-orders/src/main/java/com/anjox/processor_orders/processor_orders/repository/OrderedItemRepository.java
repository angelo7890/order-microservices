package com.anjox.processor_orders.processor_orders.repository;

import com.anjox.processor_orders.processor_orders.model.OrderedItemModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderedItemRepository extends JpaRepository<OrderedItemModel, UUID> {
}
