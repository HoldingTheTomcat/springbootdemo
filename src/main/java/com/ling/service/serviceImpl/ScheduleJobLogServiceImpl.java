package com.ling.service.serviceImpl;

import com.ling.dao.entity.ScheduleJobLogEntity;
import com.ling.dao.mapper.ScheduleJobLogEntityMapper;
import com.ling.service.ScheduleJobLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("scheduleJobLogService")
public class ScheduleJobLogServiceImpl implements ScheduleJobLogService {
	
	@Autowired
	private ScheduleJobLogEntityMapper mapper;
	
	@Override
	public ScheduleJobLogEntity queryObject(Long jobId) {
		return mapper.selectByPrimaryKey(jobId);
	}

	@Override
	public List<ScheduleJobLogEntity> queryList(Map<String, Object> map) {
		return mapper.selectByExample(map);
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
		return mapper.selectCountByExample(map);
	}

	@Override
	public void save(ScheduleJobLogEntity log) {
		mapper.insert(log);
	}

}
