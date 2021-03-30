package com.hcbxwy.rabbitmq;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 连接工具类
 *
 * @author Billson
 * @date 2021/3/29 20:03
 */
public class ConnectionUtil {

    public static Connection newConnection() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        // 主机地址
        factory.setHost("localhost");
        factory.setPort(5672);
        factory.setVirtualHost("/learn-rabbitmq");
        factory.setUsername("learn-rabbitmq");
        factory.setPassword("123456");
        return factory.newConnection();
    }
}
