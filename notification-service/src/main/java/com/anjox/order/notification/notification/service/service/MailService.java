package com.anjox.order.notification.notification.service.service;

import com.anjox.order.notification.notification.service.model.OrderModel;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailService {
    private final JavaMailSender mailSender;
    public MailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }
    Logger logger = LoggerFactory.getLogger(MailService.class);

    @Value("${spring.mail.username}")
    private String emailSender;

    public void sendNotification(OrderModel order) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            helper.setFrom(new InternetAddress(emailSender, "Order API"));
            helper.setTo(order.getEmail());
            helper.setSubject("Order");
            helper.setText(generateMessage(order), false);
            mailSender.send(mimeMessage);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
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
