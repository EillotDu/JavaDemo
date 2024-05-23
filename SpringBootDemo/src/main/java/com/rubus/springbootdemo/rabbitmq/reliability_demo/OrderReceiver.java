package com.rubus.springbootdemo.rabbitmq.reliability_demo;

import com.rabbitmq.client.Channel;
import com.rubus.springbootdemo.Bean.OrderInfo;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeoutException;

@Component
public class OrderReceiver {

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "order.queue", declare = "true"),
            exchange = @Exchange(name = "order.exchange", declare = "true", type = ExchangeTypes.TOPIC),
            key = "order.message"
    ))
    @RabbitHandler
    public void onOrderMessage(@Payload OrderInfo order, @Header Map<String, Object> headers, Channel channel) throws IOException, TimeoutException {
        //消费者操作
        try {
            System.out.println("------收到消息，开始消费------");
            System.out.println("订单ID：" + order.getOrderId());

            Long deliveryTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
            // 现在手动确认消息ACK
            //multiple：这是一个 boolean 类型的参数。
            // 如果设置为 true，则会确认所有比 deliveryTag 小的未被确认的消息（批量确认）。
            // 如果设置为 false，则只确认 deliveryTag 指定的单条消息。
            channel.basicAck(deliveryTag, false);
        } finally {
            channel.close();
        }
    }
}
