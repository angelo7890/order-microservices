package com.anjox.order.notification.notification.service.listener;

import com.anjox.order.notification.notification.service.model.OrderModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class OrderListener {
    private final Logger logger = LoggerFactory.getLogger(OrderListener.class);

    public OrderListener(Queue queue) {
    }

    @RabbitListener(queues ="${rabbitmq.queue.name}")
    public void sendNotification(OrderModel order) {
        logger.info("notificacao gerada: "+ order.toString());

    }
}
