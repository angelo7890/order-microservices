package com.anjox.order.notification.notification.service.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${rabbitmq.exchange.created.order.name}")
    private String exchangeCreatedOrder;

    @Value("${rabbitmq.exchange.processed.order.name}")
    private String exchangeProcessedOrder;

    @Value("${rabbitmq.queue.created.order.name}")
    private String queueCreatedOrder;

    @Value("${rabbitmq.queue.processed.order.name}")
    private String queueProcessedOrder;

    //------------------------------------------------------------------------------------------------------------------

    @Bean
    public FanoutExchange exchangeProcessedOrder() {
        return new FanoutExchange(exchangeProcessedOrder);
    }

    @Bean
    public FanoutExchange exchangeCreatedOrder() {
        return new FanoutExchange(exchangeCreatedOrder);
    }

    //------------------------------------------------------------------------------------------------------------------

    @Bean
    public Queue queueProcessedOrder() {
        return new Queue(queueProcessedOrder, true);
    }

    @Bean
    public Queue queueCreatedOrder() {
        return new Queue(queueCreatedOrder, true);
    }

    //------------------------------------------------------------------------------------------------------------------
    @Bean
    public Binding bindingCreatedOrder() {
        return BindingBuilder.bind(queueCreatedOrder()).to(exchangeCreatedOrder());
    }

    @Bean
    public Binding bindingProcessedOrder() {
        return BindingBuilder.bind(queueProcessedOrder()).to(exchangeProcessedOrder());
    }

    //------------------------------------------------------------------------------------------------------------------

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, MessageConverter messageConverter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter);
        return rabbitTemplate;
    }

    @Bean
    public ApplicationListener<ApplicationReadyEvent> AplicationReadyEventApplicationListener(RabbitAdmin rabbitAdmin) {
        return event -> rabbitAdmin.initialize();
    }
}
