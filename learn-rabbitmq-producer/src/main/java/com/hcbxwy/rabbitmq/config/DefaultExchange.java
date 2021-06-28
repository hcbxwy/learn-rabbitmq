package com.hcbxwy.rabbitmq.config;

/**
 * 默认交换机
 *
 * @author Billson
 * @date 2021/6/24 09:46
 */
public class DefaultExchange {

    /**
     * 路由模式交换机（也叫直接模式）
     */
    public static final String DIRECT = "amq.direct";
    /**
     * 广播模式交换机（也叫发布订阅模式）
     */
    public static final String FANOUT = "amq.fanout";
    /**
     * 主题模式交换机（也叫通配符模式）
     */
    public static final String TOPIC = "amq.topic";
}
