package com.fast.rabbitmq.routing;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RoutingConsumer {


    @RabbitListener(queues = "queue.routing.one")
    public void processOne(String name) {
        System.out.println("queue.routing.one：" + name);
    }

    @RabbitListener(queues = "queue.routing.two")
    public void processTwo(String name) {
        System.out.println("queue.routing.two：" + name);
    }

    @RabbitListener(queues = "queue.routing.three")
    public void processThree(String name) {
        System.out.println("queue.routing.three：" + name);
    }

}