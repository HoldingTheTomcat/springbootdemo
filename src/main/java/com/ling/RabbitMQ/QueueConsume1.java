package com.ling.RabbitMQ;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author TianHeLing
 * @Description
 * @date 2019/5/10
 */
@Component
@RabbitListener(queues = "helloRabbit")
public class QueueConsume1 {

    @RabbitHandler
    public void showMessage(String message) {
        System.out.println("helloRabbit接收到消息：" + message);
    }
}
