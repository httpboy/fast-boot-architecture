package com.fast.rabbitmq.topic;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class topicConfig {
    @Bean
    Queue topicOne() {
        Queue queue = new Queue("queue.topic.one", true);
        return queue;
    }

    @Bean
    Queue topicTwo() {
        Queue queue = new Queue("queue.topic.two", true);
        return queue;
    }

    @Bean
    Queue topicThree() {
        Queue queue = new Queue("queue.topic.three", true);
        return queue;
    }

    @Bean
    TopicExchange topicExchange() {
        TopicExchange directExchange = new TopicExchange("topicExchange");
        return directExchange;
    }

    @Bean
    Binding bindingTopicOne(Queue topicOne, TopicExchange topicExchange) {
        Binding binding = BindingBuilder.bind(topicOne).to(topicExchange).with("#.error");
        return binding;
    }

    @Bean
    Binding bindingTopicTwo(Queue topicTwo, TopicExchange topicExchange) {
        Binding binding = BindingBuilder.bind(topicTwo).to(topicExchange).with("#.log");
        return binding;
    }

    @Bean
    Binding bindingTopicThree(Queue topicThree, TopicExchange topicExchange) {
        Binding binding = BindingBuilder.bind(topicThree).to(topicExchange).with("good.#.timer");
        return binding;
    }
}
