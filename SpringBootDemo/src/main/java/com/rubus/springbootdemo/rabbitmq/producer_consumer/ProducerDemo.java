package com.rubus.springbootdemo.rabbitmq.producer_consumer;

import com.rubus.springbootdemo.Bean.User;
import jakarta.annotation.Resource;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ProducerDemo {

    @Resource
    private RabbitTemplate rabbitTemplate;

    public void produce() {
        String message = new Date() + "ShangHai";
        System.out.println("producer str: " + message);
        rabbitTemplate.convertAndSend("rabbitmq_queue", message);
    }

    public void produceUser() {
        User user = new User("Rubus", "123");
        System.out.println("producer user: " + user);
        rabbitTemplate.convertAndSend("rabbitmq_queue_object", user);
    }
}
