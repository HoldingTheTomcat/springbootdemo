package com.ling.controller;

import com.ling.dao.entity.Student;
import com.ling.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by LingZi on 2018/11/21.
 */
@RestController
public class SpringController {

    private Logger logger = LoggerFactory.getLogger(getClass());


    @Autowired
    private StudentService studentService;

    @GetMapping("addStudent")
    public Student addStudent()throws Exception {
        Student student = new Student();
        student.setDogName("lisi");
        student.setAge(10);
        studentService.insertStudent(student);
        return student;
    }

    @RequestMapping("validStudent")
    public String validStudent(@Valid Student student) {
        logger.info("name: {}" ,student.getDogName());
        String aa = "";
        return "/jsp/editSelect";
    }

    @RequestMapping("getStudentList")
    public List<Student> getStudentList() {
        List<Student> studentList = studentService.getStudentList();
        return studentList;
    }
    
  
}
