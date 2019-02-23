package com.ling.common.activeMQ;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * Created by LingZi on 2018/11/20.
 */
@Component
@Slf4j
public class TopicListener {
    
    @JmsListener(destination = "publish.topic", containerFactory = "jmsListenerContainerTopic")
    public void receive(String message) {
        log.info("receive message: {}", message);
        try {
            // mqProcess.process(message); //做相应的业务处理
            System.out.println(message);
        } catch (Exception e) {
            log.error("receiveMessage err:{}", e);
        }
    }
}
