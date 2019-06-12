package com.ling.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;

/**
 * @author TianHeLing
 * @Description
 * @date 2019/6/12
 */
public class NewsJobListener implements JobListener {

    // 为监听器命名
    @Override
    public String getName() {
        return "myJobListener";
    }

    @Override
    public void jobToBeExecuted(JobExecutionContext jobExecutionContext) {
        System.out.println(getName() + "触发对" + jobExecutionContext.getJobDetail().getJobClass() + "开始执行的监听工作，这个位置可以做资源准备，入职记录等工作");
    }

    /**
     * 否决执行时被调用
     * 监听触发器
     */
    @Override
    public void jobExecutionVetoed(JobExecutionContext jobExecutionContext) {
        System.out.println("否决执行，可以做些日志");
    }

    @Override
    public void jobWasExecuted(JobExecutionContext jobExecutionContext, JobExecutionException e) {
        System.out.println(getName()+"触发对"+ jobExecutionContext.getJobDetail().getJobClass() +"结束执行的监听工作，这个位置可以结果统计工作，资源销爱工作。");
    }
}
