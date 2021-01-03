package com.fast.rabbitmq.batchptp;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.BatchingRabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class BatchPointProducer {
    @Autowired
    BatchingRabbitTemplate batchQueueRabbitTemplate;

    public void send(String routingKey, Message message, CorrelationData correlationData) {
        //批量发送
        batchQueueRabbitTemplate.convertAndSend(routingKey, message, correlationData);
    }
}