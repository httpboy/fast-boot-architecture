package com.fast.rabbitmq.ptp;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class PointConsumer {
    /**
     * queues 监听的队列名
     *
     * @param name
     */
    @RabbitListener(queues = "point.to.point")
    public void processOne(String name) {
        System.out.println("point.to.point：" + name);
    }
}