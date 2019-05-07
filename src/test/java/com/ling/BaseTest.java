package com.ling;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by LingZi on 2018/11/21.
 * 基本Test
 */
@SpringBootTest(classes = SpringbootdemoApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class BaseTest {
    
}

