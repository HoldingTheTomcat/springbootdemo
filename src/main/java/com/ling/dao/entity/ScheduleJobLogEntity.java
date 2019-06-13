package com.ling.dao.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "schedule_job_log")
public class ScheduleJobLogEntity {
    /**
     * 日志id
     */
    @Id
    @Column(name = "log_id")
    private Long logId;

    /**
     * 任务id
     */
    @Column(name = "job_id")
    private Long jobId;

    /**
     * spring bean名称
     */
    @Column(name = "bean_Name")
    private String beanName;

    /**
     * 方法名
     */
    @Column(name = "method_name")
    private String methodName;

    /**
     * 参数
     */
    private String params;

    /**
     * 任务状态    0：成功    1：失败
     */
    private Integer status;

    /**
     * 失败信息
     */
    private String error;

    /**
     * 耗时(单位：毫秒)
     */
    private Long times;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 获取日志id
     *
     * @return log_id - 日志id
     */
    public Long getLogId() {
        return logId;
    }

    /**
     * 设置日志id
     *
     * @param logId 日志id
     */
    public void setLogId(Long logId) {
        this.logId = logId;
    }

    /**
     * 获取任务id
     *
     * @return job_id - 任务id
     */
    public Long getJobId() {
        return jobId;
    }

    /**
     * 设置任务id
     *
     * @param jobId 任务id
     */
    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    /**
     * 获取spring bean名称
     *
     * @return bean_Name - spring bean名称
     */
    public String getBeanName() {
        return beanName;
    }

    /**
     * 设置spring bean名称
     *
     * @param beanName spring bean名称
     */
    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    /**
     * 获取方法名
     *
     * @return method_name - 方法名
     */
    public String getMethodName() {
        return methodName;
    }

    /**
     * 设置方法名
     *
     * @param methodName 方法名
     */
    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    /**
     * 获取参数
     *
     * @return params - 参数
     */
    public String getParams() {
        return params;
    }

    /**
     * 设置参数
     *
     * @param params 参数
     */
    public void setParams(String params) {
        this.params = params;
    }

    /**
     * 获取任务状态    0：成功    1：失败
     *
     * @return status - 任务状态    0：成功    1：失败
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置任务状态    0：成功    1：失败
     *
     * @param status 任务状态    0：成功    1：失败
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取失败信息
     *
     * @return error - 失败信息
     */
    public String getError() {
        return error;
    }

    /**
     * 设置失败信息
     *
     * @param error 失败信息
     */
    public void setError(String error) {
        this.error = error;
    }

    /**
     * 获取耗时(单位：毫秒)
     *
     * @return times - 耗时(单位：毫秒)
     */
    public Long getTimes() {
        return times;
    }

    /**
     * 设置耗时(单位：毫秒)
     *
     * @param times 耗时(单位：毫秒)
     */
    public void setTimes(Long times) {
        this.times = times;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}