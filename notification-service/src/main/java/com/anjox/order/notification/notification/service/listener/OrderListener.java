package com.anjox.order.notification.notification.service.listener;
import com.anjox.order.notification.notification.service.model.OrderModel;
import com.anjox.order.notification.notification.service.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class OrderListener {
    private final Logger logger = LoggerFactory.getLogger(OrderListener.class);

    private final MailService mailService;

    public OrderListener(MailService mailService) {
        this.mailService = mailService;
    }

    @RabbitListener(queues ="${rabbitmq.queue.created.order.name}")
    public void sendNotificationCreatedOrder(OrderModel order) {
        mailService.sendNotification(order);
        logger.info("notificacao de criacao de pedido gerada: "+ order.toString());
    }


   @RabbitListener(queues = "${rabbitmq.queue.processed.order.name}")
   public void sendNotificationProcessedOrder(OrderModel order) {
        mailService.sendNotification(order);
        logger.info("notificacao de processamento de pedido gerada: "+ order.toString());
   }

}
