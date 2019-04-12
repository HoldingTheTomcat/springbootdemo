package com.ling;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ling.controller.SpringController;
import com.ling.dao.entity.Student;
import com.ling.dao.entity.Teacher;
import com.ling.dao.mapper.StudentMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * Created by LingZi on 2018/11/21.
 */
@SpringBootTest(classes = SpringbootdemoApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class TestSpringController {

    private static Logger logger = LoggerFactory.getLogger(TestMain.class);

    @Autowired
    private SpringController springController;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private Environment environment;

   
    
    @Test
    public void  test1(){
       
    }

    @Test
    public void testRedis() {
        List<Student> students = studentMapper.selectAll();
        System.out.println("1111");
        System.out.println("size:"+ students.size());
        for (Student student : students) {
            System.out.println("name:"+ student.getDogName());
        }
    }


    @Autowired
    private StudentMapper studentMapper;
    
    @Test
    public void testPage(){
        // 分页
        PageHelper.startPage(1, 5);
        
        // 方式一，直接查询
        List<Student> list = studentMapper.selectAll();
        
        // 方式二，使用example查询
        Example example = new Example(Student.class);
        List<Student> students = studentMapper.selectByExample(example);
        
        PageInfo<Student> studentPageInfo = new PageInfo<>(students);
        System.out.println("size:"+studentPageInfo.getSize());
    }
}
