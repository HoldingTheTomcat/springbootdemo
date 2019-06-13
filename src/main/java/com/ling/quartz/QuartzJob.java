package com.ling.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * @author TianHeLing
 * @Description 定时任务
 * @date 2019/6/13
 */
public class QuartzJob extends QuartzJobBean {


    /**
     * 定时执行的方法，这里可以直接获得jobDetail里面设置的类名、方法名，然后反射执行
     */
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("执行任务");
    }
}
