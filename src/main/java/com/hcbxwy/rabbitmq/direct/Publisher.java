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
public class Publisher {

    public static final String DIRECT_EXCHANGE = "direct_exchange";
    public static final String DIRECT_QUEUE_1 = "direct_queue_1";
    public static final String DIRECT_QUEUE_2 = "direct_queue_2";

    public static void main(String[] args) throws Exception {
        try (Connection connection = ConnectionUtil.newConnection();
             Channel channel = connection.createChannel()) {

            channel.exchangeDeclare(DIRECT_EXCHANGE, BuiltinExchangeType.DIRECT);
            channel.queueDeclare(DIRECT_QUEUE_1, true, false, false, null);
            channel.queueDeclare(DIRECT_QUEUE_2, true, false, false, null);
            channel.queueBind(DIRECT_QUEUE_1, DIRECT_EXCHANGE, "queue_1");
            channel.queueBind(DIRECT_QUEUE_2, DIRECT_EXCHANGE, "queue_2");

            for (int i = 1; i <= 50; i++) {
                String message = String.format("爱妃，我爱你第%d遍", i);
                channel.basicPublish(DIRECT_EXCHANGE, "queue_1", null, message.getBytes());
                System.err.println(String.format("已发送消息：%s", message));
            }
        }
    }
}
