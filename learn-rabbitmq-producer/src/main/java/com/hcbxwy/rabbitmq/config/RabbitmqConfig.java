package com.hcbxwy.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * rabbitmq配置类
 *
 * @author Billson
 * @date 2021/3/31 20:23
 */
@Configuration
public class RabbitmqConfig {

    /**
     * 交换机名称
     */
    public final static String MY_TOPIC_EXCHANGE = "my.topic";
    /**
     * 队列名称
     */
    public static final String TOPIC_QUEUE = "topic.queue";

    /**
     * 声明交换机
     */
    @Bean("myTopicExchange")
    public Exchange myTopicExchange() {
        return ExchangeBuilder.topicExchange(MY_TOPIC_EXCHANGE).durable(true).build();
    }

    /**
     * 声明队列
     */
    @Bean
    public Queue topicQueue() {
        return QueueBuilder.durable(TOPIC_QUEUE).build();
    }

    /**
     * 绑定队列和交换机
     */
    @Bean
    public Binding topicQueueExchange(@Qualifier("topicQueue") Queue topicQueue,
                                      @Qualifier("myTopicExchange") Exchange myTopicExchange) {
        return BindingBuilder.bind(topicQueue).to(myTopicExchange).with("topic.#").noargs();
    }
}
