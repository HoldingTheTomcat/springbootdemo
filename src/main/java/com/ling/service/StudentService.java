package com.ling.service;

import com.ling.dao.entity.Student;

/**
 * @author TianHeLing
 * @Description
 * @date 2018/12/9
 */
public interface StudentService {

    void updateStudent(Student student);

    void insertStudent(Student student);
}
