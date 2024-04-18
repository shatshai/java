package com.example.springbootpubsub.Listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.example.springbootpubsub.Interface.MessageInterface;

@Component
public class MessageListener {

    @Value("${rabbitmq.queue}")
    String  queueName;

    @Value("${rabbitmq.exchange}")
    String exchange;

    @RabbitListener(queues = "${rabbitmq.queue}")
    public void handleMessage(MessageInterface message) {
        System.out.println("Received message from queue " + queueName + ": " + message.getAction() + ": " + message.getMessageType() + ": " + message.getId());
    }
}
