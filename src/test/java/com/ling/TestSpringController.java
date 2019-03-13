package com.ling;

import com.ling.controller.SpringController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by LingZi on 2018/11/21.
 */
@SpringBootTest(classes = SpringbootdemoApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class TestSpringController {

    private static Logger logger = LoggerFactory.getLogger(TestMain.class);

    @Autowired
    private SpringController springController;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private Environment environment;
    
    @Test
    public void  test1(){
       
    }

    @Test
    public void testRedis() {
        // String name = stringRedisTemplate.opsForValue().get("name");
        // logger.info("name:{}", name);
        String property = environment.getProperty("ling.ge.name");
        logger.info(property);
    }
}
