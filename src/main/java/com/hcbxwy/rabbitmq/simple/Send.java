package com.hcbxwy.rabbitmq.simple;

import com.hcbxwy.rabbitmq.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 * 发送消息（简单模式）
 *
 * @author Billson
 * @date 2021/3/29 19:46
 */
public class Send {
    private final static String QUEUE_NAME = "hello";

    public static void main(String[] argv) throws Exception {
        try (Connection connection = ConnectionUtil.newConnection();
             Channel channel = connection.createChannel()) {
            /*
             * 参数1：队列名称
             * 参数2：是否定义持久化，若是则rabbitmq重启后消息还在
             * 参数3：是否独占本次连接
             * 参数4：是否在不使用的时候自动删除队列
             * 参数5：队列其他参数
             */
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            String message = "Hello World!";
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");
        }
    }
}
