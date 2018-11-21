package com.ling.activeMQ;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

/**
 * Created by LingZi on 2018/11/20.
 */
@Component
@Slf4j
public class QueueListener {

    @JmsListener(destination = "publish.queue", containerFactory = "jmsListenerContainerQueue")
    @SendTo("out.queue")
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
