package com.anjox.order.notification.notification.service.service;

import com.anjox.order.notification.notification.service.model.OrderModel;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {
    private final JavaMailSender mailSender;
    public MailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendNotification(OrderModel order) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("Order-api");
        message.setTo(order.getEmail());
        message.setSubject("order");
        message.setText(this.generateMessage(order));
        mailSender.send(message);
    }

    private String generateMessage(OrderModel order) {
        String orderId = order.getId().toString();
        String client = order.getClient();
        String totalPrice = String.valueOf(order.getTotalPrice());
        String orderStatus = order.getStatus().name();

        return String.format(
                "SEU PEDIDO\n" +
                        "ID: %s\n" +
                        "Cliente: %s\n" +
                        "Valor total: %s\n" +
                        "Status: %s\n",
                orderId, client, totalPrice, orderStatus
        );
    }
}
