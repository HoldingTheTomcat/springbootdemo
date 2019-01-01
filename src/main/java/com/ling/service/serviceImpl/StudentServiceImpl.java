package com.ling.service.serviceImpl;

import com.ling.dao.entity.Student;
import com.ling.dao.mapper.StudentMapper;
import com.ling.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author TianHeLing
 * @Description
 * @date 2018/12/9
 */

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;
    
    @Override
    public void updateStudent(Student student) {
        studentMapper.updateByPrimaryKeySelective(student);
    }

    @Override
    @Transactional
    public void insertStudent(Student student) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        studentMapper.insert(student);
        // int i = 1 / 0;
    }
}
