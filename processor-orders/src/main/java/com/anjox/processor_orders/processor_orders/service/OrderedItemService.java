package com.anjox.processor_orders.processor_orders.service;

import com.anjox.processor_orders.processor_orders.model.OrderModel;
import com.anjox.processor_orders.processor_orders.model.OrderedItemModel;
import com.anjox.processor_orders.processor_orders.repository.OrderedItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderedItemService {
    private final OrderedItemRepository orderedItemRepository;

    public OrderedItemService(OrderedItemRepository orderedItemRepository) {
        this.orderedItemRepository = orderedItemRepository;
    }

    public List<OrderedItemModel> save(List<OrderedItemModel> items) {
        return orderedItemRepository.saveAll(items);
    }

    private void save(OrderedItemModel item) {
        orderedItemRepository.save(item);
    }

    public void updatedItems(List<OrderedItemModel> items, OrderModel order) {
        items.forEach(i -> {
            // informando ao item o seu pedido
            i.setOrder(order);
            this.save(i);
        });
    }
}
