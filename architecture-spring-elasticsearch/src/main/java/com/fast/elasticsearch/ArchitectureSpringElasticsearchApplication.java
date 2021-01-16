package com.fast.elasticsearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * elasticsearch 版本7.10.1
 * lucene_version 版本8.7.0
 * Spring Boot Starter AMQP 版本2.4.1
 */
@SpringBootApplication
public class ArchitectureSpringElasticsearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(ArchitectureSpringElasticsearchApplication.class, args);
    }

}
