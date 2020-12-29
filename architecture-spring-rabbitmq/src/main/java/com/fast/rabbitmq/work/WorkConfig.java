package com.fast.rabbitmq.work;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WorkConfig {

    @Bean
    public Queue work() {
        Queue queue = new Queue("WorkingMode", true);
        return queue;
    }
}
