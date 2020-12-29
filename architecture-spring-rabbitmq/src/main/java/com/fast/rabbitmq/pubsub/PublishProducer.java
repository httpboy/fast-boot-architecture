package com.fast.rabbitmq.pubsub;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PublishProducer {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(String sendMsg) {
        //指定队列
        this.rabbitTemplate.convertAndSend("publishExchange", "", sendMsg);
    }
}