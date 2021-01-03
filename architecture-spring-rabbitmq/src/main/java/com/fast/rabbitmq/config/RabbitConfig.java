package com.fast.rabbitmq.config;

import com.fast.rabbitmq.domain.MqMessage;
import com.fast.rabbitmq.util.CacheUtil;
import com.google.gson.Gson;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.batch.BatchingStrategy;
import org.springframework.amqp.rabbit.batch.SimpleBatchingStrategy;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.BatchingRabbitTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import javax.annotation.PostConstruct;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 消息生产者利用确认模式以及回退模式，可以确保数据不丢失。
 * <p>
 * 这些发送失败的数据可以保存在redis、mysql，然后看具体业务何时异步处理这些失败数据
 */
@Configuration
public class RabbitConfig {
    @Autowired
    RabbitTemplate rabbitTemplate;

    //    /**
//     * 设置序列化类型
//     * @return
//     */
//    @Bean
//    public MessageConverter jsonMessageConverter(){
//        return new Jackson2JsonMessageConverter();
//    }

    @PostConstruct
    public void initRabbitTemplate() {
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {

            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                String msgId = correlationData.getId();
                System.out.println("ConfirmCallback:     " + "相关数据：" + correlationData);
                System.out.println("ConfirmCallback:     " + "确认情况：" + ack);
                System.out.println("ConfirmCallback:     " + "原因：" + cause);
                if (ack) {
                    //消息投递到交换机成功
                    System.out.println("ConfirmCallback 消息投递到交换机成功,消息id : " + correlationData.getId());
                } else {
                    //消息投递到交换机失败
                    CacheUtil.set(msgId, new String(correlationData.getReturnedMessage().getBody()));
                    System.out.println("ConfirmCallback 消息投递到交换机失败,消息id : " + correlationData.getId());
                }
                ConcurrentHashMap hashMap = CacheUtil.hashMap;
                System.out.println("ConfirmCallback 发送失败数据：" + new Gson().toJson(hashMap.keySet()));
            }

        });

        rabbitTemplate.setReturnsCallback(new RabbitTemplate.ReturnsCallback() {

            @Override
            public void returnedMessage(ReturnedMessage returnedMessage) {
                String content = new String(returnedMessage.getMessage().getBody());
                MqMessage mqMessage = new Gson().fromJson(content, MqMessage.class);
                CacheUtil.set(mqMessage.getMessageId(), new Gson().toJson(mqMessage));
                ConcurrentHashMap hashMap = CacheUtil.hashMap;

                System.out.println("ReturnCallback:     " + "消息：" + returnedMessage.getMessage());
                System.out.println("ReturnCallback:     " + "回应码：" + returnedMessage.getReplyCode());
                System.out.println("ReturnCallback:     " + "回应信息：" + returnedMessage.getReplyText());
                System.out.println("ReturnCallback:     " + "交换机：" + returnedMessage.getExchange());
                System.out.println("ReturnCallback:     " + "路由键：" + returnedMessage.getRoutingKey());

                System.out.println("ReturnCallback 发送失败数据：" + new Gson().toJson(hashMap.keySet()));
            }


        });
    }

}