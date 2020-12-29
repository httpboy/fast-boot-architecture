package com.fast.rabbitmq.pubsub;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class PublishConsumer {
    @RabbitListener(queues = "queue.publish.one")
    public void processOne(String name) {
        System.out.println("queue.publish.one：" + name);
    }

    @RabbitListener(queues = "queue.publish.two")
    public void processTwo(String name) {
        System.out.println("queue.publish.two：" + name);
    }

    @RabbitListener(queues = "queue.publish.three")
    public void processThree(String name) {
        System.out.println("queue.publish.three：" + name);
    }
}