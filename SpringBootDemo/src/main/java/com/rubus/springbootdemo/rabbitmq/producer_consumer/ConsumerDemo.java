package com.rubus.springbootdemo.rabbitmq.producer_consumer;

import com.rubus.springbootdemo.Bean.User;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ConsumerDemo {

    @RabbitHandler
    @RabbitListener(queuesToDeclare = @Queue("rabbitmq_queue"))
    public void process(String message) {
        System.out.println("consumer str: " + message);
    }

    @RabbitHandler
    @RabbitListener(queuesToDeclare = @Queue("rabbitmq_queue_object"))
    public void process(User user) {
        System.out.println("consumer user: " + user);
    }
}
