package com.hcbxwy.rabbitmq.work;

import com.hcbxwy.rabbitmq.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;

import java.nio.charset.StandardCharsets;

/**
 * 新任务（工作模式）
 *
 * @author Billson
 * @date 2021/3/29 20:17
 */
public class NewTask {

    private static final String TASK_QUEUE_NAME = "task_queue";

    public static void main(String[] argv) throws Exception {
        try (Connection connection = ConnectionUtil.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(TASK_QUEUE_NAME, true, false, false, null);

            for (int i = 1; i <= 50; i++) {
                String message = String.format("爱妃，我爱你第%s遍.", i);

                channel.basicPublish("", TASK_QUEUE_NAME,
                        MessageProperties.PERSISTENT_TEXT_PLAIN,
                        message.getBytes(StandardCharsets.UTF_8));
                System.out.println(message);
            }
        }
    }
}
