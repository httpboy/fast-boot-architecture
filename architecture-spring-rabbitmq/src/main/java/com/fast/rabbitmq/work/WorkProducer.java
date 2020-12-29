package com.fast.rabbitmq.work;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WorkProducer {
    @Autowired
    RabbitTemplate rabbitTemplate;

    public void send(String sendMsg) {
        rabbitTemplate.convertAndSend("WorkingMode", sendMsg);
    }
}
