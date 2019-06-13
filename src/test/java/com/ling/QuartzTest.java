package com.ling;

import com.ling.quartz.ScheduleUtils;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author TianHeLing
 * @Description
 * @date 2019/6/13
 */
public class QuartzTest extends BaseTest {

    @Autowired
    private Scheduler scheduler;
    
    public void testQuattz1(){
        ScheduleUtils.deleteScheduleJob(scheduler,null);
    }
}
