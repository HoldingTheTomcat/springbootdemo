package com.ling;

import com.ling.dao.entity.Student;
import com.ling.dao.mapper.StudentMapper;
import com.mysql.jdbc.StringUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.weekend.Weekend;
import tk.mybatis.mapper.weekend.WeekendCriteria;
import tk.mybatis.mapper.weekend.WeekendSqls;

import java.util.List;

/**
 * @author TianHeLing
 * @Description
 * @date 2019/4/16
 */
public class RabbitmqTest extends BaseTest {


    private static Logger logger = LoggerFactory.getLogger(TestMain.class);

    @Autowired
    private RabbitTemplate  rabbitTemplate;

    @Test
    public void testSendToQueue() {

        rabbitTemplate.convertAndSend("helloRabbit","你好");
    }
    
    @Test
    public void testSendToExchangeFanout(){
        rabbitTemplate.convertAndSend("lingChange", "","你好,我是Fanout消息");
    }
    
    @Test
    public void testSendToExchangeTopic(){
        rabbitTemplate.convertAndSend("topictest", "good.log","你好,我是Topic消息");
    }


}
