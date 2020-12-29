package com.fast.rabbitmq;

import com.fast.rabbitmq.ptp.PointProducer;
import com.fast.rabbitmq.pubsub.PublishProducer;
import com.fast.rabbitmq.routing.RoutingProducer;
import com.fast.rabbitmq.topic.TopicProducer;
import com.fast.rabbitmq.work.WorkProducer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
class ArchitectureSpringRabbitmqApplicationTests {

    @Autowired
    PointProducer pointProducer;

    @Autowired
    WorkProducer workProducer;

    @Autowired
    PublishProducer publishProducer;

    @Autowired
    RoutingProducer routingProducer;


    @Autowired
    TopicProducer topicProducer;

    /**
     * 点对点模式（一个队列一个消费者）
     */
    @Test
    void toPoint() {
        String sendMsg = "点对点队列:" + "toPoint" + new Date();
        pointProducer.send(sendMsg);
    }

    /**
     * 工作模式(一个队列，多个消费者)
     * <p>
     * 注意看时间，说明消息是轮询分发的，一个消息只由一个消费者消费。
     */
    @Test
    void work() {
        String sendMsg = "工作模式:" + "work" + new Date();
        workProducer.send(sendMsg);
    }

    /**
     * 发布订阅者模式(创建队列、交换机，将队列绑定到交换机)
     * 交换机会将消息推送到所有绑定到它的队列
     */
    @Test
    void publishSubscribe() {
        String sendMsg = "发布订阅模式:" + "publishSubscribe" + new Date();
        publishProducer.send(sendMsg);
    }

    /**
     * 路由模式
     */
    @Test
    void routing() {

        for (int i = 1; i <= 3; i++) {
            String sendMsg = i + "";
            routingProducer.send(sendMsg);
        }
    }

    /**
     * 主题模式(Topic)
     */
    @Test
    void topic() {
        topicProducer.send("测试.error", "测试.error");
        topicProducer.send("测试.log", "测试.log");
        topicProducer.send("good.测试.timer", "good.测试.timer");
    }
}
