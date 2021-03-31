package com.hcbxwy.rabbitmq.controller;

import com.hcbxwy.rabbitmq.config.RabbitmqConfig;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 发送消息控制类
 *
 * @author Billson
 * @date 2021/3/31 20:36
 */
@RestController
@RequestMapping("/message")
@AllArgsConstructor
public class SendMessageController {

    private final RabbitTemplate rabbitTemplate;

    /**
     * 发送消息
     *
     * @param msg 消息内容
     * @param key 路由key
     * @return java.lang.String
     * @author Billson
     * @date 2021/3/31 下午8:41
     */
    @GetMapping("/send")
    public String send(@RequestParam String msg, @RequestParam String key) {
        rabbitTemplate.convertAndSend(RabbitmqConfig.MY_TOPIC_EXCHANGE, key, msg);
        return "发送消息成功！";
    }
}
