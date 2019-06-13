package com.ling.dao.mapper;

import com.ling.dao.entity.ScheduleJob;
import com.tk.mybatis.BaseMapper;

public interface ScheduleJobMapper extends BaseMapper<ScheduleJob> {
    int deleteBatch(Object[] id);
}