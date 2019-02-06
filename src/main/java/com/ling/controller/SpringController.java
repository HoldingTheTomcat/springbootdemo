package com.ling.controller;

import com.ling.dao.entity.Student;
import com.ling.dao.mapper.StudentMapper;
import com.ling.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * Created by LingZi on 2018/11/21.
 */
@Controller
public class SpringController {

    private Logger logger = LoggerFactory.getLogger(getClass());


    @Autowired
    private StudentService studentService;

    @GetMapping("test")
    public String toUplaod4(Student student,String classRoom)throws Exception {
      /*  System.out.println("jin来了a255e2eee22");
        student.setAge(12);
        studentService.insertStudent(student);*/
        logger.debug("debug");
        logger.info("info1");
        logger.error("error1");
        return "/jsp/editSelect";
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
