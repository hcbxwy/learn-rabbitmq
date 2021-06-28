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

    private static final String DIRECT_EXCHANGE = "directExchange";
    private static final String TOPIC_EXCHANGE = "topicExchange";
    private static final String FANOUT_EXCHANGE = "fanoutExchange";

    /**
     * 直连交换机（路由模式交换机）
     */
    @Bean(DIRECT_EXCHANGE)
    public Exchange directExchange() {
        return ExchangeBuilder.directExchange(DefaultExchange.DIRECT).durable(true).build();
    }

    /**
     * 主题交换机（通配符模式/发布订阅交换机）
     */
    @Bean(TOPIC_EXCHANGE)
    public Exchange topicExchange() {
        return ExchangeBuilder.topicExchange(DefaultExchange.TOPIC).durable(true).build();
    }

    /**
     * 广播交换机
     */
    @Bean(FANOUT_EXCHANGE)
    public Exchange fanoutExchange() {
        return ExchangeBuilder.fanoutExchange(DefaultExchange.FANOUT).durable(true).build();
    }

    /**
     * 主题队列
     */
    @Bean
    public Queue topicQueue() {
        return QueueBuilder.durable(QueueName.TOPIC_QUEUE).build();
    }

    /**
     * 广播队列
     */
    @Bean
    public Queue fanoutQueue() {
        return QueueBuilder.durable(QueueName.FANOUT_QUEUE).build();
    }

    /**
     * 直连队列
     */
    @Bean
    public Queue directQueue() {
        return QueueBuilder.durable(QueueName.DIRECT_QUEUE).build();
    }

    /**
     * 主题队列绑定到主题交换机
     */
    @Bean
    public Binding topicQueueBinding(@Qualifier("topicQueue") Queue topicQueue,
                                      @Qualifier(TOPIC_EXCHANGE) Exchange topicExchange) {
        return BindingBuilder.bind(topicQueue).to(topicExchange).with("topic.#").noargs();
    }

    /**
     * 广播队列绑定到广播交换机
     */
    @Bean
    public Binding fanoutQueueBinding(@Qualifier("fanoutQueue") Queue fanoutQueue,
                                     @Qualifier(FANOUT_EXCHANGE) Exchange fanoutExchange) {
        return BindingBuilder.bind(fanoutQueue).to(fanoutExchange).with("").noargs();
    }

    /**
     * 直连队列绑定到直连交换机
     */
    @Bean
    public Binding directQueueBinding(@Qualifier("directQueue") Queue directQueue,
                                     @Qualifier(DIRECT_EXCHANGE) Exchange directExchange) {
        return BindingBuilder.bind(directQueue).to(directExchange).with("direct").noargs();
    }
}
