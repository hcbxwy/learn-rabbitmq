package com.hcbxwy.rabbitmq.pb;

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

    public static final String FANOUT_EXCHANGE = "fanout_exchange";
    public static final String FANOUT_QUEUE_1 = "fanout_queue_1";
    public static final String FANOUT_QUEUE_2 = "fanout_queue_2";

    public static void main(String[] args) throws Exception {
        try (Connection connection = ConnectionUtil.newConnection();
             Channel channel = connection.createChannel()) {

            channel.exchangeDeclare(FANOUT_EXCHANGE, BuiltinExchangeType.FANOUT);
            channel.queueDeclare(FANOUT_QUEUE_1, true, false, false, null);
            channel.queueDeclare(FANOUT_QUEUE_2, true, false, false, null);
            channel.queueBind(FANOUT_QUEUE_1, FANOUT_EXCHANGE, "");
            channel.queueBind(FANOUT_QUEUE_2, FANOUT_EXCHANGE, "");

            for (int i = 1; i <= 50; i++) {
                String message = String.format("爱妃，我爱你第%d遍", i);
                channel.basicPublish(FANOUT_EXCHANGE, "", null, message.getBytes());
                System.err.println(String.format("已发送消息：%s", message));
            }
        }
    }
}
