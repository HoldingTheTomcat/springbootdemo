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
import org.springframework.data.redis.core.StringRedisTemplate;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.weekend.Weekend;
import tk.mybatis.mapper.weekend.WeekendCriteria;
import tk.mybatis.mapper.weekend.WeekendSqls;

import java.util.List;

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


    @Test
    public void test1() {

    }

    @Test
    public void testRedis() {
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
