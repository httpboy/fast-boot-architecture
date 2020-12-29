package com.fast.rabbitmq.topic;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TopicProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(String routing, String sendMsg) {
        //指定队列
        this.rabbitTemplate.convertAndSend("topicExchange", routing, sendMsg);

    }
}