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

    @RabbitListener(queues = "topic.queue")
    public void handleMessage(String message) {
        System.err.println("消息接收器收到的消息：" + message);
    }
}
