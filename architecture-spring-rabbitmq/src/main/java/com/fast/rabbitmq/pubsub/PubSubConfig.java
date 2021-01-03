package com.fast.rabbitmq.pubsub;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * fanout
 * 发布/订阅者模式（Publish/Subscribe）
 */
@Configuration
public class PubSubConfig {
    /**
     * 当没有这个队列的时候会自动创建
     *
     * @return
     */
    @Bean
    Queue publishOne() {
        Queue queue = new Queue("queue.publish.one", true);
        return queue;
    }

    /**
     * 当没有这个队列的时候会自动创建
     *
     * @return
     */
    @Bean
    Queue publishTwo() {
        Queue queue = new Queue("queue.publish.two", true);
        return queue;
    }

    /**
     * 当没有这个队列的时候会自动创建
     *
     * @return
     */
    @Bean
    Queue publishThree() {
        Queue queue = new Queue("queue.publish.three", true);
        return queue;
    }

    /**
     * 创建交换器
     *
     * @return
     */
    @Bean
    FanoutExchange pulishExchange() {
        FanoutExchange directExchange = new FanoutExchange("publishExchange");
        return directExchange;
    }

    /**
     * 绑定队列(不用指定routing key),参数名字要和bean名字一致
     *
     * @param publishOne
     * @param pulishExchange
     * @return
     */
    @Bean
    Binding bindingPublishOne(Queue publishOne, FanoutExchange pulishExchange) {
        Binding binding = BindingBuilder.bind(publishOne()).to(pulishExchange());
        return binding;
    }

    @Bean
    Binding bindingPublishTwo(Queue publishTwo, FanoutExchange pulishExchange) {
        Binding binding = BindingBuilder.bind(publishTwo).to(pulishExchange);
        return binding;
    }

    @Bean
    Binding bindingPublishThree(Queue publishThree, FanoutExchange pulishExchange) {
        Binding binding = BindingBuilder.bind(publishThree).to(pulishExchange);
        return binding;
    }
}
