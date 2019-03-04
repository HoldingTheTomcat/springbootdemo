package com.ling.service;

import com.ling.dao.entity.Student;

import java.util.List;

/**
 * @author TianHeLing
 * @Description
 * @date 2018/12/9
 */
public interface StudentService {


    void insertStudent(Student student);

    List<Student> getStudentList();
}
