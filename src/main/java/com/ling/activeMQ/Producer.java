package com.ling.activeMQ;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.jms.Queue;
import javax.jms.Topic;

/**
 * Created by LingZi on 2018/11/20.
 */
@Component
public class Producer {

    @Autowired
    private JmsMessagingTemplate jms;

    @Autowired
    private Queue queue;

    @Autowired
    private Topic topic;

    public void queue(String message) {
        jms.convertAndSend(queue, message);
    }

    @JmsListener(destination = "out.queue")
    public void consumerMsg(String msg) {
        System.out.println(msg);
    }

    public void topic(String message) {
        jms.convertAndSend(topic, message);
    }
}
