package com.rubus.springbootdemo;

import com.rubus.springbootdemo.rabbitmq.ProducerDemo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBootDemoApplicationTests {

    @Autowired
    ProducerDemo producerDemo;

    @Test
    void contextLoads() throws InterruptedException {
        producerDemo.produce();
        Thread.sleep(5000);
    }
    @Test
    void contextLoads1() throws InterruptedException {
        producerDemo.produceUser();
        Thread.sleep(5000);
    }

}
