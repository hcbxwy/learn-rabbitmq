package com.hcbxwy.rabbitmq.pb;

import com.hcbxwy.rabbitmq.ConnectionUtil;
import com.rabbitmq.client.*;

import java.nio.charset.StandardCharsets;

/**
 * 订阅者1
 *
 * @author Billson
 * @date 2021/3/29 20:53
 */
public class Subscriber1 {

    public static void main(String[] args) throws Exception {
        Connection connection = ConnectionUtil.newConnection();
        Channel channel = connection.createChannel();

        // 创建消费者，并设置消息处理
        DefaultConsumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) {
                System.err.println("路由key为：" + envelope.getRoutingKey());
                System.err.println("交换机为：" + envelope.getExchange());
                System.err.println("消息ID为：" + envelope.getDeliveryTag());
                System.err.println("订阅者1收到的消息：" + new String(body, StandardCharsets.UTF_8));
            }
        };

        // 监听消息
        /*
         * 参数1：队列名称
         * 参数1：是否自动确认
         * 参数1：消息收到后回调
         */
        channel.basicConsume(Publisher.FANOUT_QUEUE_1, true, consumer);
    }
}
