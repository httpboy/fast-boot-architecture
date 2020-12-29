package com.fast.rabbitmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * erl 版本11.1.4
 * rabbitmq-server 版本3.8.9
 * Spring Boot Starter AMQP 版本2.4.1
 */
@SpringBootApplication
public class ArchitectureSpringRabbitmqApplication {

    public static void main(String[] args) {
        SpringApplication.run(ArchitectureSpringRabbitmqApplication.class, args);
    }

}
