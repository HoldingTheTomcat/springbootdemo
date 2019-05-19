package com.ling.service.serviceImpl;

import com.ling.dao.entity.Student;
import com.ling.dao.mapper.StudentMapper;
import com.ling.dao.mapper.test2.StudentTest2Mapper;
import com.ling.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author TianHeLing
 * @Description
 * @date 2018/12/9
 */

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentTest2Mapper studentTestMapper;

    @Autowired
    private StudentMapper studentMapper;
    
   /* @Override
    public void updateStudent(Student student) {
        studentTestMapper.updateByPrimaryKeySelective(student);
    }*/

    /**
     * 如果不是默认数据源对应的事务管理器，那么必须指定其对应的事务管理器，不然事务注解不起作用
     * @param student
     */
    @Override
    public void insertStudent(Student student) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        studentTestMapper.insert(student);
        int i = 1 / 0;
    }

    
    
    
    
    
    
    
    
    @Override
    // @Transactional
    public List<Student> getStudentList() {
        List<Student> students = studentMapper.selectAll();
        System.out.println("111111111111//////////////");
        List<Student> students2 = studentMapper.selectAll();
        return students2;
        
    }
    
    

}
