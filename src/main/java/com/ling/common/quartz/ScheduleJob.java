package com.ling.common.quartz;

import com.ling.common.util.SpringContextUtils;
import com.ling.dao.entity.ScheduleJobEntity;
import com.ling.dao.entity.ScheduleJobLogEntity;
import com.ling.service.ScheduleJobLogService;
import org.apache.commons.lang3.StringUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author TianHeLing
 * @Description 定时任务
 * @date 2019/6/13
 */
public class ScheduleJob extends QuartzJobBean {

    private Logger logger = LoggerFactory.getLogger(getClass());
    private ExecutorService service = Executors.newSingleThreadExecutor();

    /**
     * 定时执行的方法，这里可以直接获得jobDetail里面设置的类名、方法名，然后反射执行
     */
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {

        // String jsonJob = context.getMergedJobDataMap().getString(ScheduleJobEntity.JOB_PARAM_KEY);
        // ScheduleJobEntity scheduleJobEntity = JSON.parseObject(jsonJob, ScheduleJobEntity.class);
        ScheduleJobEntity scheduleJobEntity = (ScheduleJobEntity) context.getMergedJobDataMap().get(ScheduleJobEntity.JOB_PARAM_KEY);

        //获取spring bean
        ScheduleJobLogService scheduleJobLogService = (ScheduleJobLogService) SpringContextUtils.getBean("scheduleJobLogService");

        //数据库保存执行记录
        ScheduleJobLogEntity log = new ScheduleJobLogEntity();
        log.setJobId(scheduleJobEntity.getJobId());
        log.setBeanName(scheduleJobEntity.getBeanName());
        log.setMethodName(scheduleJobEntity.getMethodName());
        log.setParams(scheduleJobEntity.getParams());
        log.setCreateTime(new Date());

        //任务开始时间
        long startTime = System.currentTimeMillis();
        try {
            //执行任务
            logger.info("任务准备执行，任务ID：" + scheduleJobEntity.getJobId());
            //反射拿到task类的执行方法
            ScheduleRunnable task = new ScheduleRunnable(scheduleJobEntity.getBeanName(),
                    scheduleJobEntity.getMethodName(), scheduleJobEntity.getParams());
            Future<?> future = service.submit(task);

            future.get();

            //任务执行总时长
            long times = System.currentTimeMillis() - startTime;
            log.setTimes(times);
            //任务状态    0：成功    1：失败
            log.setStatus(0);

            logger.info("任务执行完毕，任务ID：" + scheduleJobEntity.getJobId() + "  总共耗时：" + times + "毫秒");
        } catch (Exception e) {
            logger.error("任务执行失败，任务ID：" + scheduleJobEntity.getJobId(), e);

            //任务执行总时长
            long times = System.currentTimeMillis() - startTime;
            log.setTimes(times);

            //任务状态    0：成功    1：失败
            log.setStatus(1);
            log.setError(StringUtils.substring(e.toString(), 0, 2000));
        } finally {
            scheduleJobLogService.save(log);
        }


    }
}
