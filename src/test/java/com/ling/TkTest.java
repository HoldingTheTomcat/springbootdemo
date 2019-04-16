package com.ling;

import com.ling.dao.entity.Student;
import com.ling.dao.mapper.StudentMapper;
import com.mysql.jdbc.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
public class TkTest extends BaseTest {


    private static Logger logger = LoggerFactory.getLogger(TestMain.class);

    @Autowired
    private StudentMapper studentMapper;

    public void test22() {
        //获得seekendsql
        WeekendSqls<Student> sqls = WeekendSqls.<Student>custom();

        //可进行动态sql拼接
        sqls = sqls
                .andEqualTo(Student::getAge, 0)
                .andLike(Student::getDogName, "%d%");
        //获得结果
        List<Student> students = studentMapper.selectByExample(
                Example
                        .builder(Student.class)
                        .where(sqls)
                        .orderByDesc("count", "name").build()
        );
    }

    // 复杂and or条件查询
    public void test23() {
        Example e = new Example(Student.class);

        //关键字查询,创建关键字criteria
        Example.Criteria c = e.createCriteria();
        String keyword = "lisi";
        if (!StringUtils.isNullOrEmpty(keyword)) {
            c.orEqualTo("userName", keyword).orEqualTo("policeNo", keyword).orEqualTo("realName", keyword);
        }

        //部门查询.创建部门criteria
        Example.Criteria criteria = e.createCriteria();
        criteria.andEqualTo("departmentId", "kaifabu");
        e.and(criteria);

        List<Student> users = studentMapper.selectByExample(e);

    }

    public void testWeekend() {
        Weekend<Student> weekend = new Weekend<>(Student.class);
        //关键字查询部分
        String keyword = "lisi";
        WeekendCriteria<Student, Object> keywordCriteria = weekend.weekendCriteria();
        if (!StringUtils.isNullOrEmpty(keyword)) {
            keywordCriteria.orLike(Student::getDogName, keyword).orLike(Student::getNeedBook, keyword).orLike(Student::getTeacher, keyword);
            //此处不需要再用下面这一句了,不然上面这个条件组合会重复一次
            //weekend.and(keywordCriteria)
        }
        //部门查询部分
        Example example = new Example(Student.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("departmentId", "kaifabu");
        weekend.and(criteria);

        List<Student> users = studentMapper.selectByExample(weekend);
    }

    public void testWeekend2() {
        Weekend<Student> weekend = new Weekend<>(Student.class);
        //关键字查询部分
        String keyword = "lisi";
        WeekendCriteria<Student, Object> keywordCriteria = weekend.weekendCriteria();
        if (!StringUtils.isNullOrEmpty(keyword)) {
            keywordCriteria.orLike(Student::getDogName, keyword).orLike(Student::getNeedBook, keyword).orLike(Student::getTeacher, keyword);
            //此处不需要再用下面这一句了,不然上面这个条件组合会重复一次
            //weekend.and(keywordCriteria)
        }
        //部门查询部分
        Example example = new Example(Student.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("departmentId", "kaifabu");
        weekend.and(criteria);

        List<Student> users = studentMapper.selectByExample(weekend);
    }

}
