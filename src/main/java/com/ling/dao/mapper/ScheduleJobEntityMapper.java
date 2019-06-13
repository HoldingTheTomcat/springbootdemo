package com.ling.dao.mapper;

import com.ling.dao.entity.ScheduleJobEntity;
import com.tk.mybatis.BaseMapper;

import java.util.Map;

public interface ScheduleJobEntityMapper extends BaseMapper<ScheduleJobEntity> {
    
    int deleteBatch(Object[] id);

    /**
     * 批量更新状态
     */
    int updateBatch(Map<String, Object> map);
}