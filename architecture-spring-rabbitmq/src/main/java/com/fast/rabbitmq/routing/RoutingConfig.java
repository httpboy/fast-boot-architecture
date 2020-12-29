package com.fast.rabbitmq.routing;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RoutingConfig {
    /**
     * 当没有这个队列的时候会自动创建
     *
     * @return
     */
    @Bean
    Queue routingOne() {
        Queue queue = new Queue("queue.routing.one", true);
        return queue;
    }

    @Bean
    Queue routingTwo() {
        Queue queue = new Queue("queue.routing.two", true);
        return queue;
    }

    @Bean
    Queue routingThree() {
        Queue queue = new Queue("queue.routing.three", true);
        return queue;
    }

    /**
     * 创建交换器
     *
     * @return
     */
    @Bean
    DirectExchange routingExchange() {
        DirectExchange directExchange = new DirectExchange("routingExchange");
        return directExchange;
    }

    /**
     * 绑定队列
     *
     * @param routingOne
     * @param routingExchange
     * @return
     */
    @Bean
    Binding bindingRoutingOne(Queue routingOne, DirectExchange routingExchange) {
        Binding binding = BindingBuilder.bind(routingOne).to(routingExchange).with("1");
        return binding;
    }

    @Bean
    Binding bindingRoutingTwo(Queue routingTwo, DirectExchange routingExchange) {
        Binding binding = BindingBuilder.bind(routingTwo).to(routingExchange).with("2");
        return binding;
    }

    @Bean
    Binding bindingRoutingThree(Queue routingThree, DirectExchange routingExchange) {
        Binding binding = BindingBuilder.bind(routingThree).to(routingExchange).with("3");
        return binding;
    }
}
