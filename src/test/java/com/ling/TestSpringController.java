package com.ling;

import com.ling.controller.SpringController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by LingZi on 2018/11/21.
 */
@SpringBootTest(classes = SpringController.class)
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class TestSpringController {

    @Autowired
    private SpringController springController;
    
    @Test
    public void  test1(){
       
    }
}
