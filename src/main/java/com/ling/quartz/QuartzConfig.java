package com.ling.quartz;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * 定时任务配置
 *
 */
@Configuration
public class QuartzConfig {

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(DataSource dataSource) {
        //quartz参数
        Properties prop = new Properties();
        //配置实例
        prop.put("org.quartz.scheduler.instanceName", "MyScheduler"); //实例名称
        prop.put("org.quartz.scheduler.instanceId", "AUTO"); 
        //线程池配置
        prop.put("org.quartz.threadPool.class", "org.quartz.simpl.SimpleThreadPool");
        prop.put("org.quartz.threadPool.threadCount", "20");
        prop.put("org.quartz.threadPool.threadPriority", "5");
        //JobStore配置存储方式
        prop.put("org.quartz.jobStore.class", "org.quartz.impl.jdbcjobstore.JobStoreTX"); //存储在数据库
        prop.put("org.quartz.jobStore.tablePrefix", "QRTZ_"); //表的浅醉，即持久化在数据库中以QRTZ为开头的表中
        
        //集群配置
        prop.put("org.quartz.jobStore.isClustered", "true");
        prop.put("org.quartz.jobStore.clusterCheckinInterval", "15000");
        prop.put("org.quartz.jobStore.maxMisfiresToHandleAtATime", "1");
        prop.put("org.quartz.jobStore.misfireThreshold", "12000");

        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        factory.setDataSource(dataSource);
        factory.setQuartzProperties(prop);
        factory.setSchedulerName("Myscheduler");
        //延时启动,应用启动30秒后，QuartzScheduler再启动
        factory.setStartupDelay(30);
        // factory.setApplicationContextSchedulerContextKey("applicationContextKey");
        // 可选，QuartzScheduler 每次重启服务器时，他有一个默认的行为，先把qrtz_job_details表里面的数据清空，
        // 启动时更新己存在的Job，这样就不用每次修改targetObject后删除qrtz_job_details表对应记录了
        factory.setOverwriteExistingJobs(true);
        // 设置自动启动，默认为true，即1 每次重启服务qrtz_job_details表里面的任务自动运行，2 每次创建完一个job实例的时候，是不是马上自动运行
        factory.setAutoStartup(true);

        return factory;
    }
}