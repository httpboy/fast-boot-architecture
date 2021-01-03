package com.fast.rabbitmq.ptp;

import org.springframework.amqp.core.Message;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class PointConsumer {
    /**
     * queues 监听的队列名
     * <p>
     * 演示-消息确认机制
     *
     * @param
     */
    @RabbitListener(queues = "point.to.point")
    public void processOne(Message message, Channel channel) {
        String msg = new String(message.getBody());
        System.out.println("point.to.point：" + msg);
        try {
            /**
             * 肯定应答
             * deliveryTag:该消息的index
             *
             * multiple：是否批量确认，true:将一次性ack所有小于deliveryTag的消息。
             * 假设我先发送三条消息deliveryTag分别是5、6、7，可它们都没有被确认，当我发第四条消息此时deliveryTag为8，
             * multiple设置为 true，会将5、6、7、8的消息全部进行确认
             */
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            /**
             * 否定应答（表示消息没有被正确处理）
             * deliveryTag:该消息的index
             * multiple：是否批量确认.true:将一次性拒绝所有小于deliveryTag的消息。
             * requeue：被拒绝的是否重新入队列头部
             *
             * 针对多个消费者消费同一个队列时（多个@RabbitListener），被丢弃的消息可能会被其他消费者捕获到。
             * 针对单一消费者，扔回头部的消息又会被自己捕捉到，从而造成死循环。
             */
//            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
            /**
             * 否定应答（表示消息没有被正确处理）
             * basic.reject用于否定确认，但与basic.nack相比有一个限制:一次只能拒绝单条消息
             * deliveryTag:该消息的index
             * requeue：被拒绝的是否重新入队列
             *
             */
//            channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}