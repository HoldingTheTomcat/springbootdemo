package com.ling;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by LingZi on 2018/11/21.
 * 基本Test
 */
@SpringBootTest(classes = SpringbootdemoApplication.class) //server没有在默认位置，那么必须使用classes注明server的位置
@RunWith(SpringRunner.class)
@WebAppConfiguration
@Transactional
@Rollback //因为是测试，所以我们不应该真的把数据插入、修改到数据库，应该回滚掉
public class BaseTest {
    
}

