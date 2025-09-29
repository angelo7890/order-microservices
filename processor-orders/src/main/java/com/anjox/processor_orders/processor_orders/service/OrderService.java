package com.anjox.processor_orders.processor_orders.service;

import com.anjox.processor_orders.processor_orders.enums.OrderStatus;
import com.anjox.processor_orders.processor_orders.model.OrderModel;
import com.anjox.processor_orders.processor_orders.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Value("${rabbitmq.exchange.processed.order.name}")
    private String exchange;

    private final Logger logger = LoggerFactory.getLogger(OrderService.class);
    private final OrderRepository orderRepository;
    private final OrderedItemService orderedItemService;

    private final ProductService productService;

    private final RabbitTemplate rabbitTemplate;

    public OrderService(OrderRepository orderRepository, OrderedItemService orderedItemService, ProductService productService, RabbitTemplate rabbitTemplate) {
        this.orderRepository = orderRepository;
        this.orderedItemService = orderedItemService;
        this.productService = productService;
        this.rabbitTemplate = rabbitTemplate;
    }

    public void save(OrderModel orderModel) {

        //setando pedido como processado
        orderModel.setStatus(OrderStatus.PROCESSED);

        //salvando os produtos
        productService.save(orderModel.getItems());

        //salvando os items do pedido
        var items = orderedItemService.save(orderModel.getItems());

        //salvando o pedido
        orderRepository.save(orderModel);

        //atualiza o item definindo a qual pedido ele faz parte
        orderedItemService.updatedItems(items, orderModel);

        //mandando pedido para fila de notificacao como processado
        enqueue(orderModel);

        logger.info("pedido salvo com sucesso: {}", orderModel.toString());
    }

    private void enqueue(OrderModel orderModel) {
        rabbitTemplate.convertAndSend(exchange, "", orderModel);
        logger.info("pedido enfileirado: {}", orderModel.toString());
    }
}
