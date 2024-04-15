package com.example.springbootpubsub.Service;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.springbootpubsub.Interface.MessageInterface;

@Service
public class QueueService {
    
    private AmqpTemplate amqpTemplate = null;

    @Value("${rabbitmq.exchange}")
    String exchange;

    @Value("${rabbitmq.routingkey}")
    private String routingkey;

    public QueueService(RabbitTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    public void publishMessage(MessageInterface customerDto) {
        amqpTemplate.convertAndSend(exchange, routingkey, customerDto);
    }
}
