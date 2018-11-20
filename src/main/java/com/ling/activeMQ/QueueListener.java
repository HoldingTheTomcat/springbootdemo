package com.ling.activeMQ;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

/**
 * Created by LingZi on 2018/11/20.
 */
@Component
public class QueueListener {

    @JmsListener(destination = "publish.queue", containerFactory = "jmsListenerContainerQueue")
    @SendTo("out.queue")
    public String receive(String text) {
        System.out.println("QueueListener: consumer-a 收到一条信息: " + text);
        return "consumer-a received : " + text;
    }
}
