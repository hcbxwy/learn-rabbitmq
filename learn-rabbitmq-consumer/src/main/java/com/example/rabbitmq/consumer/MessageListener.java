package com.example.rabbitmq.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 消息接收器
 *
 * @author Billson
 * @date 2021/3/31 21:41
 */
@Component
public class MessageListener {

    @RabbitListener(queues = QueueName.DIRECT_QUEUE)
    public void handleDirectQueue(String message) {
        System.err.printf("队列%s收到的消息：%s%n", QueueName.DIRECT_QUEUE, message);
    }

    @RabbitListener(queues = QueueName.TOPIC_QUEUE)
    public void handleTopicQueue(String message) {
        System.err.printf("队列%s收到的消息：%s%n", QueueName.TOPIC_QUEUE, message);
    }

    @RabbitListener(queues = QueueName.FANOUT_QUEUE)
    public void handleFanoutQueue(String message) {
        System.err.printf("队列%s收到的消息：%s%n", QueueName.FANOUT_QUEUE, message);
    }
}
