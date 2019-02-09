package com.ling.controller;

import com.ling.dao.entity.Student;
import com.ling.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Created by LingZi on 2018/11/21.
 */
@RestController
public class SpringController {

    private Logger logger = LoggerFactory.getLogger(getClass());


    @Autowired
    private StudentService studentService;

    @GetMapping("test")
    public Student toUplaod4(Student student,String classRoom)throws Exception {
        student = new Student();
        student.setDogName("lisi");
        student.setAge(10);
        studentService.insertStudent(student);
        return student;
    }

    @RequestMapping("test2")
    @ResponseBody
    public String toUplaod42() {
        String aa = "";
        return "/jsp/editSelect";
    }
    
    public void getName(String name,Integer age){
        System.out.println(2);
        return;
    }
}
