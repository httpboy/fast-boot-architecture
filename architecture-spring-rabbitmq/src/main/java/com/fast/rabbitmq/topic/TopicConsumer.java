package com.fast.rabbitmq.topic;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class TopicConsumer {
    @RabbitListener(queues = "queue.topic.one")
    public void processOne(String name) {
        System.out.println("queue.topic.one：" + name);
    }

    @RabbitListener(queues = "queue.topic.two")
    public void processTwo(String name) {
        System.out.println("queue.topic.two：" + name);
    }

    @RabbitListener(queues = "queue.topic.three")
    public void processThree(String name) {
        System.out.println("queue.topic.three：" + name);
    }

}