package com.anjox.processor_orders.processor_orders.listener;

import com.anjox.processor_orders.processor_orders.enums.OrderStatus;
import com.anjox.processor_orders.processor_orders.model.OrderModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class OrderListener {
    private Logger log = LoggerFactory.getLogger(OrderListener.class);

    @RabbitListener(queues = "${rabbitmq.queue.name}")
    public void process(OrderModel orderModel) {
        orderModel.setStatus(OrderStatus.PROCESSED);
        log.info("pedido processo {}", orderModel.toString());
    }
}
