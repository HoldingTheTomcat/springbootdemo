package com.ling.gms.test2.service.serviceImpl;

import com.ling.datasource.annotation.DataSource;
import com.ling.gms.test2.dao.entity.OldStudent;
import com.ling.gms.test2.dao.mapper.OldStudentMapper;
import com.ling.gms.test2.service.OldStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author TianHeLing
 * @Description
 * @date 2019/2/13
 */
@Service
public class OldStudentServiceImpl implements OldStudentService {

    @Autowired
    private OldStudentMapper oldStudentMapper;

    @Override
    @Transactional
    public void insertStudent(OldStudent student) {
        oldStudentMapper.updateByPrimaryKeySelective(student);
        // int i = 1 / 0;
    }

    @Override
    @Transactional
    public OldStudent getStudentById(Integer id) {
        OldStudent oldStudent = oldStudentMapper.selectByPrimaryKey(id);
        return oldStudent;
    }
}
