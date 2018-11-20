package com.ling.activeMQ;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * Created by LingZi on 2018/11/20.
 */
@Component
public class TopicListener {
    
    @JmsListener(destination = "publish.topic", containerFactory = "jmsListenerContainerTopic")
    public void receive(String text) {
        System.out.println("TopicListener: consumer-a 收到一条信息: " + text);
    }
}
