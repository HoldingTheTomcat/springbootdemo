package com.ling;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by LingZi on 2018/11/21.
 * 基本Test
 */
@SpringBootTest(classes = SpringbootdemoApplication.class) //server没有在默认位置，那么必须使用classes注明server的位置
@RunWith(SpringRunner.class)
@WebAppConfiguration
public class BaseTest {
    
}

