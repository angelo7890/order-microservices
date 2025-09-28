package com.anjox.processor_orders.processor_orders.service;

import com.anjox.processor_orders.processor_orders.model.OrderModel;
import com.anjox.processor_orders.processor_orders.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final Logger logger = LoggerFactory.getLogger(OrderService.class);
    private final OrderRepository orderRepository;
    private final OrderedItemService orderedItemService;

    private final ProductService productService;


    public OrderService(OrderRepository orderRepository, OrderedItemService orderedItemService, ProductService productService) {
        this.orderRepository = orderRepository;
        this.orderedItemService = orderedItemService;
        this.productService = productService;
    }

    public void save(OrderModel orderModel) {

        //salvando os produtos
        productService.save(orderModel.getItems());

        //salvando os items do pedido
        var items = orderedItemService.save(orderModel.getItems());

        //salvando o pedido
        orderRepository.save(orderModel);

        //atualiza o item definindo a qual pedido ele faz parte
        orderedItemService.updatedItems(items, orderModel);

        logger.info("pedido salvo com sucesso: {}", orderModel.toString());
    }
}
