package com.fast.rabbitmq.controller;

import com.fast.rabbitmq.batchptp.BatchPointProducer;
import com.fast.rabbitmq.builder.MqMessageBuilder;
import com.fast.rabbitmq.domain.MqMessage;
import com.fast.rabbitmq.ptp.PointProducer;
import com.fast.rabbitmq.pubsub.PublishProducer;
import com.fast.rabbitmq.routing.RoutingProducer;
import com.fast.rabbitmq.topic.TopicProducer;
import com.fast.rabbitmq.util.UUIDGenerator;
import com.fast.rabbitmq.work.WorkProducer;
import com.google.gson.Gson;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Date;

@RestController
public class TestController {

    @Autowired
    PointProducer pointProducer;

    @Autowired
    WorkProducer workProducer;

    @Autowired
    PublishProducer publishProducer;

    @Autowired
    RoutingProducer routingProducer;


    @Autowired
    TopicProducer topicProducer;

    @Autowired
    BatchPointProducer batchPointProducer;

    /**
     * 点对点模式（一个队列一个消费者）
     */
    @GetMapping(value = "toPoint")
    public void toPoint() {
        String routingKey = "point.to.point";
        String sendMsg = "点对点队列:" + "toPoint" + new Date();
        String uuid = UUIDGenerator.newUUID();
        MqMessage mqMessage = new MqMessageBuilder()
                .setMessageId(uuid)
                .setMessageData(sendMsg)
                .setMessageTimestamp(System.currentTimeMillis())
                .create();

        CorrelationData correlationData = new CorrelationData(uuid);
        Message message = MessageBuilder.withBody(new Gson().toJson(mqMessage).getBytes())
                .setContentType(MessageProperties.CONTENT_TYPE_TEXT_PLAIN)
                .setCorrelationId(Arrays.toString(correlationData.getId().getBytes())).build();
        correlationData.setReturnedMessage(message);
        pointProducer.send(routingKey, message, correlationData);
    }

    /**
     * 点对点模式,批量发送（一个队列一个消费者）
     */
    @GetMapping(value = "toPointBatch")
    public void toPointBatch() {
        String routingKey = "batch.queue";
        String sendMsg = "点对点队列:" + new Date();


        for (int i = 0; i < 1000; i++) {
            String uuid = UUIDGenerator.newUUID();
            MqMessage mqMessage = new MqMessageBuilder()
                    .setMessageId(uuid)
                    .setMessageData(sendMsg + "-" + i)
                    .setMessageTimestamp(System.currentTimeMillis())
                    .create();

            CorrelationData correlationData = new CorrelationData(uuid);
            Message message = MessageBuilder.withBody(new Gson().toJson(mqMessage).getBytes())
                    .setContentType(MessageProperties.CONTENT_TYPE_TEXT_PLAIN)
                    .setCorrelationId(Arrays.toString(correlationData.getId().getBytes())).build();
            correlationData.setReturnedMessage(message);
            batchPointProducer.send(routingKey, message, correlationData);
        }
    }

    /**
     * 测试确认模式以及回退模式
     */
    @GetMapping(value = "testConfirmAndReturnMode")
    public void testConfirmAndReturnMode() {
        String sendMsg = "点对点队列:" + "toPoint" + new Date();
        pointProducer.send2(sendMsg);
    }

    /**
     * 工作模式(一个队列，多个消费者)
     * <p>
     * 注意看时间，说明消息是轮询分发的，一个消息只由一个消费者消费。
     */
    @GetMapping(value = "work")
    public void work() {
        String sendMsg = "工作模式:" + "work" + new Date();
        workProducer.send(sendMsg);
    }

    /**
     * 发布订阅者模式(创建队列、交换机，将队列绑定到交换机)
     * 交换机会将消息推送到所有绑定到它的队列
     */
    @GetMapping(value = "publishSubscribe")
    public void publishSubscribe() {
        String sendMsg = "发布订阅模式:" + "publishSubscribe" + new Date();
        publishProducer.send(sendMsg);
    }

    /**
     * 路由模式
     */
    @GetMapping(value = "routing")
    public void routing() {

        for (int i = 1; i <= 3; i++) {
            String sendMsg = i + "";
            routingProducer.send(sendMsg);
        }
    }

    /**
     * 主题模式(Topic)
     */
    @GetMapping(value = "topic")
    public void topic() {
        topicProducer.send("测试.error", "测试.error");
        topicProducer.send("测试.log", "测试.log");
        topicProducer.send("good.测试.timer", "good.测试.timer");
    }
}
