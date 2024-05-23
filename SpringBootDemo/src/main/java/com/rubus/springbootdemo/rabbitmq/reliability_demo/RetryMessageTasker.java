package com.rubus.springbootdemo.rabbitmq.reliability_demo;

import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class RetryMessageTasker {
    private static final Logger LOGGER = LoggerFactory.getLogger(RetryMessageTasker.class);

    @Resource
    private RabbitOrderSender rabbitOrderSender;

    /**
     * 定时任务
     */
    @Scheduled(initialDelay = 5000, fixedDelay = 10000)
    public void reSend() {
        //抽取消息状态为0且已经超时的消息集合
//        List<MessageLog> list = messageLogMapper.query4StatusAndTimeoutMessage();
//        list.forEach(messageLog -> {
//            //投递三次以上的消息
//            if(messageLog.getTryCount() >= 3){
//                //更新失败的消息
//                messageLogMapper.changeMessageLogStatus(messageLog.getMessageId(), Constans.ORDER_SEND_FAILURE, new Date());
//            } else {
//                // 重试投递消息，将重试次数递增
//                messageLogMapper.update4ReSend(messageLog.getMessageId(),  new Date());
//                OrderInfo reSendOrder = JsonUtil.jsonToObject(messageLog.getMessage(), OrderInfo.class);
//                try {
//                    rabbitOrderSender.sendOrder(reSendOrder);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    logger.error("-----------异常处理-----------");
//                }
//            }
//        });
    }
}
