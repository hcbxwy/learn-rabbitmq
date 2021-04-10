package com.hcbxwy.rabbitmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * 项目启动类
 *
 * @author Billson
 * @date 2021/3/31 20:17
 */
@SpringBootApplication
@ImportResource("classpath:/spring/spring-rabbitmq.xml")
public class ProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProducerApplication.class);
    }
}
