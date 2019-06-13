package com.ling.quartz;

import org.springframework.stereotype.Component;

/**
 * @author TianHeLing
 * @Description
 * @date 2019/6/13
 */
@Component
public class BackUpDBTask {
    
    public void backUp(String projectName){
        System.out.println("备份数据库：" + projectName);
    }
}
