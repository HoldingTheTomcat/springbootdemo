package com.ling.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.ling.dao.entity.Student;
import com.ling.dao.entity.StudentNew;
import com.ling.dao.mapper.StudentNewMapper;
import com.ling.manager.facade.UserManagerFacade;
import com.ling.service.StudentService;
import io.swagger.annotations.*;
import jdk.nashorn.internal.objects.NativeUint8Array;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by LingZi on 2018/11/21
 */
@RestController
public class SpringController {

    private Logger logger2 = LoggerFactory.getLogger(getClass());

    @Autowired
    private StudentNewMapper studentNewMapper;


    /**
     * 注意：启动项目，如果没加 dataSource.init(); 那么启动项目不进行数据库操作的情况下，不会增加连接
     * 写个测试循环耗尽数据库链接，注意在Controller层，不要在同一个事务
     * 再次执行 show full processlist; 查看连接是不是 增加到了 InitialSize 个链接（排除别人也在连的情况）
     */
    @GetMapping("getStudentNewList")
    public List<StudentNew> getStudentNewList(String studentid, String name) {

        List<StudentNew> studentList = null;
        for (int i = 0; i < 10000; i++) {
            studentList = studentNewMapper.selectAll();
            try {
                Thread.sleep(100l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return studentList;
    }


}
