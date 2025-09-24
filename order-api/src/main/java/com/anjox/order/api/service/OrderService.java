package com.anjox.order.api.service;

import com.anjox.order.api.model.OrderModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final RabbitTemplate rabbitTemplate;
    public OrderService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }
    private final Logger log = LoggerFactory.getLogger(OrderService.class);

    @Value("${rabbitmq.exchange.name}")
    private String Exchange;

    public OrderModel enqueue(OrderModel orderModel) {
        rabbitTemplate.convertAndSend(Exchange, "", orderModel);
        log.info("pedido enfileirado: {}", orderModel.toString());
        return orderModel;
    }
}
