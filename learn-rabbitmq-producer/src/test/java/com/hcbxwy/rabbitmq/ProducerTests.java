package com.hcbxwy.rabbitmq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 单元测试类
 *
 * @author Billson
 * @date 2021/4/10 22:41
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProducerTests {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void ttlQueueTest() {
        // 路由键与队列名相同
        rabbitTemplate.convertAndSend("my_ttl_queue", "发送过期队列 my_ttl_queue，6秒内不消费则自动删除");
    }
}
