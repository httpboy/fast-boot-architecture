package com.fast.rabbitmq.batchptp;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.batch.BatchingStrategy;
import org.springframework.amqp.rabbit.batch.SimpleBatchingStrategy;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.BatchingRabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * 批量发送配置类
 */
@Configuration
public class BatchPTPConfig {


    /**
     * durable:是否持久化,默认是false
     * true 持久化队列：会被存储在磁盘上,当消息代理重启时仍然存在，
     * false 暂存队列：当前连接有效
     *
     * @return
     */
    @Bean
    Queue toPointBatch() {
        Queue queue = new Queue("batch.queue", true);
        return queue;
    }

    @Bean("batchQueueTaskScheduler")
    public TaskScheduler batchQueueTaskScheduler() {
        TaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        return taskScheduler;
    }


    /**
     * 批量处理rabbitTemplate
     *
     * @param connectionFactory
     * @param taskScheduler
     * @return
     */
    @Bean
    public BatchingRabbitTemplate batchQueueRabbitTemplateCreate(ConnectionFactory connectionFactory,
                                                                 @Qualifier("batchQueueTaskScheduler") TaskScheduler taskScheduler) {

        //!!!重点： 所谓批量， 就是spring 将多条message重新组成一条message, 发送到mq, 从mq接受到这条message后，在重新解析成多条message

        //一次批量的数量
        int batchSize = 10;
        // 缓存大小限制,单位字节，
        // simpleBatchingStrategy的策略，是判断message数量是否超过batchSize限制或者message的大小是否超过缓存限制，
        // 缓存限制，主要用于限制"组装后的一条消息的大小"
        // 如果主要通过数量来做批量("打包"成一条消息), 缓存设置大点
        // 详细逻辑请看simpleBatchingStrategy#addToBatch()
        int bufferLimit = 1024; //1 K
        long timeout = 10000;

        //注意，该策略只支持一个exchange/routingKey
        //A simple batching strategy that supports only one exchange/routingKey
        BatchingStrategy batchingStrategy = new SimpleBatchingStrategy(batchSize, bufferLimit, timeout);
        BatchingRabbitTemplate batchingRabbitTemplate = new BatchingRabbitTemplate(connectionFactory, batchingStrategy, taskScheduler);
        batchingRabbitTemplate.setMandatory(true);
        return batchingRabbitTemplate;
    }

    @Bean("batchQueueRabbitListenerContainerFactory")
    public SimpleRabbitListenerContainerFactory batchQueueRabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        //设置批量
        factory.setBatchListener(true);
        factory.setConsumerBatchEnabled(true);//设置BatchMessageListener生效
        factory.setBatchSize(10);//设置监听器一次批量处理的消息数量
        factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        return factory;
    }
}
