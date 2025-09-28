package com.anjox.processor_orders.processor_orders.service;

import com.anjox.processor_orders.processor_orders.model.OrderedItemModel;
import com.anjox.processor_orders.processor_orders.model.ProductModel;
import com.anjox.processor_orders.processor_orders.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void save(List<OrderedItemModel> items) {
        items.forEach(i -> {
            productRepository.save(i.getProduct());
        });
    }
}
