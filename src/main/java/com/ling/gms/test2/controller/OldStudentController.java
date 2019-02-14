package com.ling.gms.test2.controller;

import com.ling.gms.test2.dao.entity.OldStudent;
import com.ling.gms.test2.service.OldStudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author TianHeLing
 * @Description
 * @date 2019/2/13
 */
@RestController
public class OldStudentController {

    private Logger logger = LoggerFactory.getLogger(getClass());


   
    
    @Autowired
    private OldStudentService studentService;

    @GetMapping("test")
    public OldStudent toUplaod4() throws Exception {
        OldStudent student = new OldStudent();
        student.setId(1);
        student.setAge(125);
        System.out.println();
        studentService.insertStudent(student);
        // studentService.insertStudent(student);
        return student;
    }
}
