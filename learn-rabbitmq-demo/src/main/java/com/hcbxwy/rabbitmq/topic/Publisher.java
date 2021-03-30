package com.hcbxwy.rabbitmq.topic;

import com.hcbxwy.rabbitmq.ConnectionUtil;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 * 发布者
 *
 * @author Billson
 * @date 2021/3/29 20:52
 */
public class Publisher {

    public static final String TOPIC_EXCHANGE = "topic_exchange";

    public static void main(String[] args) throws Exception {
        try (Connection connection = ConnectionUtil.newConnection();
             Channel channel = connection.createChannel()) {

            channel.exchangeDeclare(TOPIC_EXCHANGE, BuiltinExchangeType.TOPIC);

            for (int i = 1; i <= 50; i++) {
                String message = String.format("爱妃，我爱你第%d遍", i);
                if (i % 2 == 0) {
                    channel.basicPublish(TOPIC_EXCHANGE, "item.abc", null, message.getBytes());
                } else {
                    channel.basicPublish(TOPIC_EXCHANGE, "abc.queue", null, message.getBytes());
                }
                System.err.println(String.format("已发送消息：%s", message));
            }
        }
    }
}
