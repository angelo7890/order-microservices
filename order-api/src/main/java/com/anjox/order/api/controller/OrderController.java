package com.anjox.order.api.controller;

import com.anjox.order.api.model.OrderModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

    private final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody OrderModel order) {
        logger.info("pedido recebido: {}", order.toString());
        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }
}
