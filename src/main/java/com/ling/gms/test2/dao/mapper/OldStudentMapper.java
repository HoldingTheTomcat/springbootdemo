package com.ling.gms.test2.dao.mapper;

import com.ling.gms.test2.dao.entity.OldStudent;
import com.ling.gms.test2.dao.entity.OldStudentExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OldStudentMapper {
    int countByExample(OldStudentExample example);

    int deleteByExample(OldStudentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(OldStudent record);

    int insertSelective(OldStudent record);

    List<OldStudent> selectByExample(OldStudentExample example);

    OldStudent selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") OldStudent record, @Param("example") OldStudentExample example);

    int updateByExample(@Param("record") OldStudent record, @Param("example") OldStudentExample example);

    int updateByPrimaryKeySelective(OldStudent record);

    int updateByPrimaryKey(OldStudent record);
}