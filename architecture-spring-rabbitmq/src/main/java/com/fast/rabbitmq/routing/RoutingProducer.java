package com.fast.rabbitmq.routing;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoutingProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(String sendMsg) {
        //指定队列
        if (sendMsg.equals("1")) {
            this.rabbitTemplate.convertAndSend("routingExchange", "1", sendMsg);
        }
        if (sendMsg.equals("2")) {
            this.rabbitTemplate.convertAndSend("routingExchange", "2", sendMsg);
        }
        if (sendMsg.equals("3")) {
            this.rabbitTemplate.convertAndSend("routingExchange", "3", sendMsg);
        }
    }
}