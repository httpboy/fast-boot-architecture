package com.fast.rabbitmq.work;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 三个队列同时监听
 */
@Component
public class WorkConsumer {
    @RabbitListener(queues = "WorkingMode")
    public void processOne(String name) {
        System.out.println("WorkingMode1：" + name);
    }

    @RabbitListener(queues = "WorkingMode")
    public void processTwo(String name) {
        System.out.println("WorkingMode2：" + name);
    }

    @RabbitListener(queues = "WorkingMode")
    public void processThree(String name) {
        System.out.println("WorkingMode3：" + name);
    }

}