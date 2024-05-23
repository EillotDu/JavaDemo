package com.rubus.springbootdemo.rabbitmq.reliability_demo;

import com.rubus.springbootdemo.Bean.OrderInfo;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class RabbitOrderSender {
    private final static Logger LOGGER = LoggerFactory.getLogger(RabbitOrderSender.class);

    @Resource
    private RabbitTemplate rabbitTemplate;

    /**
     * Broker应答后，会调用该方法区获取应答结果
     */
    final RabbitTemplate.ConfirmCallback confirmCallback = (correlationData, ack, cause) -> {
        LOGGER.info("correlationData: " + correlationData);
        String messageId = correlationData.getId();
        LOGGER.info("消息确认返回值: " + ack);
        if (ack) {
            //如果返回成功，则进行更新
            //messageLogMapper.changeMessageLogStatus(messageId, Constants.ORDER_SEND_SUCCESS,new Date());
        } else {
            LOGGER.error("error: " + cause);
        }
    };

    public synchronized void sendOrder(OrderInfo orderInfo) {
        // 通过实现 ConfirmCallback接口，消息发送到Broker后出发回调，确认消息是否到达Broker服务
        // 也就是之确认是否正确到达Exchange中
        rabbitTemplate.setConfirmCallback(confirmCallback);
        // 消息唯一ID
        CorrelationData correlationData = new CorrelationData(orderInfo.getMessageId());
        rabbitTemplate.convertAndSend("order.exchange", "order.message", orderInfo, correlationData);
    }

}
