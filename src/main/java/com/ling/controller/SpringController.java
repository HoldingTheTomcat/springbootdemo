package com.ling.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.ling.dao.entity.Student;
import com.ling.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by LingZi on 2018/11/21.
 */
@RestController
public class SpringController {

    private Logger logger = LoggerFactory.getLogger(getClass());


    @Autowired
    private StudentService studentService;

    @GetMapping("addStudent")
    public Student addStudent() throws Exception {
        Student student = new Student();
        student.setDogName("lisi");
        //master-增加
        student.setAge(10);
        studentService.insertStudent(student);
        return student;
    }


    @RequestMapping("validStudent")
    public String validStudent(@Valid Student student) {
        logger.info("name: {}", student.getDogName());
        String aa = "";
        return "/jsp/editSelect";
    }

    @RequestMapping("getStudentList")
    public List<Student> getStudentList() {
        List<Student> studentList = studentService.getStudentList();
        return studentList;
    }

    @RequestMapping("gackJson")
    public String gackJson() throws IOException {
        ObjectMapper jackSonMapper = new ObjectMapper();
        Student student = new Student();
        student.setDogName("lisi");
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


}
