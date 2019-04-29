package com.ling;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ling.controller.SpringController;
import com.ling.dao.entity.Student;
import com.ling.dao.mapper.StudentMapper;
import com.mysql.jdbc.StringUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.weekend.Weekend;
import tk.mybatis.mapper.weekend.WeekendCriteria;
import tk.mybatis.mapper.weekend.WeekendSqls;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author TianHeLing
 * @Description
 * @date 2019/4/16
 */
public class ControllerTest extends BaseTest {


    private static Logger logger = LoggerFactory.getLogger(TestMain.class);

    @Autowired
    private SpringController springController;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private Environment environment;

    @Autowired
    private RedisTemplate redisTemplate;


    @Test
    public void test1() {
        redisTemplate.opsForValue().set("dog:name", "wangwang");
        String name = (String)redisTemplate.opsForValue().get("dog:name");
        stringRedisTemplate.opsForValue().set("dog:age","10");
        String o = stringRedisTemplate.opsForValue().get("dog:age");
        System.out.println("redisTemplate-get-age:"+o);
        System.out.println("stringRedisTemplate-get-name:"+name);
    }

    @Test
    public void testRedis(){
        
        redisTemplate.opsForValue().set("dog", "wangwang");
        System.out.println("执行完毕");
    }
    
    @Autowired
    public void setRedis(){
        //set :String
        redisTemplate.opsForValue().set("test:set1", "testValue1");

        //set :Object 对象
        Student student1 = new Student();
        student1.setAge(10);
        student1.setDogName("wangzai");
        //向redis里存入Student对象，并设置过期时间
        redisTemplate.opsForValue().set("student", student1, 20, TimeUnit.SECONDS);
        
        //set :Set 向指定key中存放set集合
        redisTemplate.opsForSet().add("test:set2", "asdf");
        redisTemplate.opsForSet().add("red_123", "1", "2", "3");
        //根据key查看集合中是否存在指定数据
        Boolean isRed_123 = stringRedisTemplate.opsForSet().isMember("red_123", "1");

        //set :Hash
        redisTemplate.opsForHash().put("hash1", "name1", "lms1");
        redisTemplate.opsForHash().put("hash1", "name2", "lms2");
        redisTemplate.opsForHash().put("hash1", "name3", "lms3");

        //根据key设置过期时间
        redisTemplate.expire("red_123", 1000, TimeUnit.MILLISECONDS);
        
        System.out.println(redisTemplate.opsForValue().get("test:set"));
        System.out.println(redisTemplate.opsForHash().get("hash1", "name1"));
    }
   
    @Test
    public void getRedis(){
        //根据key获取过期时间
        Long expire = redisTemplate.getExpire("student");
        System.out.println("expire:"+ expire);

        //根据key获取过期时间并换算成指定单位
        Long expireSeconds = redisTemplate.getExpire("student", TimeUnit.SECONDS);

        //检查key是否存在，返回boolean值 
        Boolean studentBoolean = stringRedisTemplate.hasKey("student");

        //根据key删除缓存
        redisTemplate.delete("student");
        
        
        Student student = (Student)redisTemplate.opsForValue().get("student");
        System.out.println(student);
    }
    
    @Test
    public void aa() {
        List<Student> students = studentMapper.selectAll();
        System.out.println("1111");
        System.out.println("size:" + students.size());
        for (Student student : students) {
            System.out.println("name:" + student.getDogName());
        }
    }


    @Autowired
    private StudentMapper studentMapper;

    @Test
    public void testPage() {
        // 分页
        PageHelper.startPage(1, 5);

        // 方式一，直接查询
        List<Student> list = studentMapper.selectAll();

        // 方式二，使用example查询
        Example example = new Example(Student.class);
        List<Student> students = studentMapper.selectByExample(example);

        PageInfo<Student> studentPageInfo = new PageInfo<>(students);
        System.out.println("size:" + studentPageInfo.getSize());
    }

   
}
