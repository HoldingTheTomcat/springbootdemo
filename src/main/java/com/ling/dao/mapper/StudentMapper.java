package com.ling.dao.mapper;

import com.ling.dao.entity.Student;
import com.ling.util.RedisCache;
import com.tk.mybatis.BaseMapper;
import org.apache.ibatis.annotations.CacheNamespace;

@CacheNamespace(implementation = RedisCache.class)
public interface StudentMapper extends BaseMapper<Student> {
}