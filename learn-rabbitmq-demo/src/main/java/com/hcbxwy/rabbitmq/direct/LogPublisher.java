package com.hcbxwy.rabbitmq.direct;

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
public class LogPublisher {

    public static final String DIRECT_EXCHANGE = "direct_exchange";
    public static final String QUEUE_INFO = "direct_info";
    public static final String QUEUE_WARN = "direct_warn";
    public static final String QUEUE_ERROR = "direct_error";

    public static void main(String[] args) throws Exception {
        try (Connection connection = ConnectionUtil.newConnection();
             Channel channel = connection.createChannel()) {

            channel.exchangeDeclare(DIRECT_EXCHANGE, BuiltinExchangeType.DIRECT);
            channel.queueDeclare(QUEUE_INFO, true, false, false, null);
            channel.queueDeclare(QUEUE_WARN, true, false, false, null);
            channel.queueDeclare(QUEUE_ERROR, true, false, false, null);
            channel.queueBind(QUEUE_INFO, DIRECT_EXCHANGE, "test");
            channel.queueBind(QUEUE_WARN, DIRECT_EXCHANGE, "prod");
            channel.queueBind(QUEUE_ERROR, DIRECT_EXCHANGE, "prod");

            for (int i = 1; i <= 50; i++) {
                String message = String.format("爱妃，我爱你第%d遍", i);
                channel.basicPublish(DIRECT_EXCHANGE, "prod", null, message.getBytes());
                System.err.printf("已发送消息：%s%n", message);
            }
        }
    }
}
