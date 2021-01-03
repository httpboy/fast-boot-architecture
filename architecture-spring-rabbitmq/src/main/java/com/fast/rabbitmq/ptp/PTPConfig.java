package com.fast.rabbitmq.ptp;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PTPConfig {


    /**
     * durable:是否持久化,默认是false
     * true 持久化队列：会被存储在磁盘上,当消息代理重启时仍然存在，
     * false 暂存队列：当前连接有效
     *
     * @return
     */
    @Bean
    Queue toPoint() {
        Queue queue = new Queue("point.to.point", true);
        return queue;
    }

    /**
     * 这个交换机是没有任何队列配置
     * <p>
     * name：交换机名字
     * durable：持久化 rabbitmq重启的时候不需要创建新的交换机
     * auto-delete：表示交换机没有在使用时将被自动删除 默认是false
     *
     * @return
     */
    @Bean
    DirectExchange lonelyDirectExchange() {
        return new DirectExchange("lonelyDirectExchange", true, false);
    }
}
