package com.ling.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.ling.dao.entity.Student;
import com.ling.manager.facade.UserManagerFacade;
import com.ling.service.StudentService;
import com.ling.util.RedisCache2;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
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
@Api(tags = "student操作接口") //修饰整个类，描述 Controller 的作用
public class SpringController {

    private Logger logger2 = LoggerFactory.getLogger(getClass());

    @GetMapping("getStudentList")
    @ApiOperation("查询所有学生") //描述一个类的一个方法，或者说一个接口
    @ApiImplicitParams({
            @ApiImplicitParam(name = "studentid",value = "学生编号",dataType = "String",paramType = "query",required = true),
            @ApiImplicitParam(name = "name",value = "学生姓名",dataType = "String",required = true)
    })
    public List<Student> getStudentList(String studentid,String name ) {
        RedisCache2 redisCache2 = new RedisCache2();
        // RedisTemplate redisTemplate = redisCache2.getRedisTemplate();
        List<Student> studentList = studentService.getStudentList();
        return studentList;
    }
    
    @PostMapping("updateStudent")
    @ApiOperation(value = "更新学生信息", notes = "只做更新")//描述一个类的一个方法，或者说一个接口
    public void  updateStudent(@ApiParam(name = "学生对象", required = true) Student student){
        
    }

    @PostMapping("deleteStudent")
    @ApiOperation(value = "删除学生信息", notes = "注意id必传")//描述一个类的一个方法，或者说一个接口
    public void deleteStudent(@ApiParam(name = "学生对象",value = "传入json格式",required = true) @RequestBody Student student) {

    }

    @Autowired
    private UserManagerFacade userManagerFacade;

    @Autowired
    private StudentService studentService;

    @GetMapping("addStudent")
    public Student addStudent(HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        Student student = new Student();
        String sessionId = session.getId();
        
        student.setDogNameNew("lisi-aaa11");
        //master-增加
        //master-增加2
        //master-增加3
        student.setDogAge(10);
        //master-继续增加
        // studentService.insertStudent(student);
        return student;
    }


    @RequestMapping("tohtml")
    public String validStudent(String name,Integer age) {
        return "index";
    }

    @RequestMapping("gackJson")
    public String gackJson() throws IOException {
        ObjectMapper jackSonMapper = new ObjectMapper();
        Student student = new Student();
        student.setDogNameNew("lisi");
        // 对象转换为字符串
        String text = jackSonMapper.writeValueAsString(student);
        // 字符串转成对象
        student = jackSonMapper.readValue(text, Student.class);

        // 对象转换为字节流
        byte[] bytes = jackSonMapper.writeValueAsBytes(student);
        // 字节流转对象
        student = jackSonMapper.readValue(bytes, Student.class);

        // 写到文件
        jackSonMapper.writeValue(new File("friend.json"), student);
        // 从文件中读取到对象
        student = jackSonMapper.readValue(new File("friend.json"), Student.class);


        //map转字符串
        Map<String, Object> map = new HashMap<>();
        map.put("age", 25);
        map.put("name", "yitian");
        map.put("interests", new String[]{"pc games", "music"});

        String textMap = jackSonMapper.writeValueAsString(map);

        //字符串转map，从JSON转换为Map对象的时候，由于Java的类型擦除，所以类型需要我们手动用new TypeReference<T>给出
        Map<String, Object> newMap = jackSonMapper.readValue(textMap, new TypeReference<Map<String, Object>>() {
        });

        //字符串转node
        JsonNode root = jackSonMapper.readTree(textMap);
        String name = root.get("name").asText();
        int age = root.get("age").asInt();

        // 美化输出
        jackSonMapper.enable(SerializationFeature.INDENT_OUTPUT);
        // 允许序列化空的POJO类（否则会抛出异常）
        jackSonMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        // 把java.util.Date, Calendar输出为数字（时间戳）
        jackSonMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        // 按Map 的key顺序输出
        jackSonMapper.enable(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS);

        // 在遇到未知属性的时候不抛出异常：原因: 如果json字符串中出现java对象中没有的属性，则在将json转换为java对象时会报错
        jackSonMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
       
        // 强制JSON 空字符串("")转换为null对象值:
        jackSonMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);

        // 将内容包裹为一个JSON属性，即每一层都用{}包裹，字符串转对象时会出错，即使抑制错误，转出的对象，属性也是空值，因为有些JSON文件需要这种结构。
        jackSonMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, true);

        return "/jsp/editSelect";
    }

    @RequestMapping("testChannel")
    public String testChannel(Integer channelid) {
        String name = userManagerFacade.getChanel(channelid).getName();
        return name;
    }

}
