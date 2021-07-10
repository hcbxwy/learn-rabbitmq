package com.hcbxwy.rabbitmq.controller;

import com.hcbxwy.rabbitmq.config.DefaultExchange;
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
     * 发送消息到主题交换机
     *
     * @param msg 消息内容
     * @param routingKey 路由key
     * @return java.lang.String
     * @author Billson
     * @date 2021/3/31 下午8:41
     */
    @GetMapping("/send/topic")
    public String sendTopic(@RequestParam String msg, @RequestParam String routingKey) {
        rabbitTemplate.convertAndSend(DefaultExchange.TOPIC, routingKey, msg);
        return "发送消息成功！";
    }

    /**
     * 发送消息到广播交换机
     *
     * @param msg 消息内容
     * @return java.lang.String
     * @author Billson
     * @date 2021/3/31 下午8:41
     */
    @GetMapping("/send/fanout")
    public String sendFanout(@RequestParam String msg) {
        rabbitTemplate.convertAndSend(DefaultExchange.FANOUT, "", msg);
        return "发送消息成功！";
    }

    /**
     * 发送消息到直连交换机
     *
     * @param msg 消息内容
     * @param routingKey 路由key
     * @return java.lang.String
     * @author Billson
     * @date 2021/3/31 下午8:41
     */
    @GetMapping("/send/direct")
    public String sendDirect(@RequestParam String msg, @RequestParam String routingKey) {
        rabbitTemplate.convertAndSend(DefaultExchange.DIRECT, routingKey, msg);
        return "发送消息成功！";
    }
}
