package com.fast.rabbitmq.batchptp;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class BatchQueueListener {

    /**
     * 批量接收处理
     *
     * @param messages
     */
    @RabbitListener(queues = "batch.queue", containerFactory = "batchQueueRabbitListenerContainerFactory")
    public void onMessageBatch(List<Message> messages, Channel channel) {
        System.out.println("BatchQueueListener batch.queue.consumer 收到" + messages.size() + "条message");
        if (messages.size() > 0) {
            System.out.println("BatchQueueListener 第一条数据是: " + new String(messages.get(0).getBody()));
        }

        for (Message message : messages) {
            try {
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}