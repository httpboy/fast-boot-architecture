package com.fast.rabbitmq.ptp;

import com.fast.rabbitmq.domain.MqMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.BatchingRabbitTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class PointProducer {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(String routingKey, Message message, CorrelationData correlationData) {
        //指定队列
        rabbitTemplate.convertAndSend(routingKey, message, correlationData);
    }



    /**
     * 测试消息推送到server，找到交换机了，但是没找到队列
     *
     * @param sendMsg
     */
    public void send2(String sendMsg) {
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString().replace("-", ""));

        String messageId = correlationData.getId();
        String messageData = sendMsg;

        MqMessage mqMessage = new MqMessage();
        mqMessage.setMessageId(messageId);
        mqMessage.setMessageData(messageData);
        mqMessage.setMessageTimestamp(System.currentTimeMillis());


        String json = new Gson().toJson(mqMessage);
        Message message = MessageBuilder.withBody(json.getBytes())
                .setContentType(MessageProperties.CONTENT_TYPE_TEXT_PLAIN)
                .setCorrelationId(Arrays.toString(messageId.getBytes())).build();
        correlationData.setReturnedMessage(message);
        rabbitTemplate.convertAndSend("lonelyDirectExchange", "point.to.point", message, correlationData);
    }
}