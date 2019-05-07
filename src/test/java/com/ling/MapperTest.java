package com.ling;

import com.ling.dao.entity.Student;
import com.ling.dao.mapper.StudentExMapper;
import com.ling.dao.mapper.StudentMapper;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

/**
 * @author TianHeLing
 * @Description
 * @date 2019/4/16
 */
public class MapperTest extends BaseTest {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private StudentExMapper studentExMapper;

    @Test
    public void testMapper() {
        // List<Student> students = studentMapper.selectEX();
        // for (Map<String, Object> student : students) {
        //     Set<String> strings = student.keySet();
        //     for (String string : strings) {
        //         System.out.println("key:"+string);
        //         System.out.println("value:"+student.get(string));
        //     }
        // }
    }

    @Test
    public void testParentMapper() {
        // List<Student> students = studentExMapper.selectAll();
        // System.out.println("一共：" + students.size());
    }

    //在需要使用的类中注入transactionTemplate
    @Autowired
    private TransactionTemplate transactionTemplate;


    public void testTransaction() {
        //不关心结果的事务执行方式 
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                try {
                    //db操作 执行事务没有返回值，例如save、update、delete等等
                } catch (Exception e) {
                    //回滚
                    status.isRollbackOnly();
                    e.printStackTrace();
                }
            }
        });
        //关心是否抛出异常
        Boolean result = transactionTemplate.execute(new TransactionCallback<Boolean>() {
            @Override
            public Boolean doInTransaction(TransactionStatus status) {
                try {
                    //db操作
                    Student student = doSomething();
                } catch (Exception e) {
                    //回滚
                    status.isRollbackOnly();
                    e.printStackTrace();
                    return false;
                }
                return true;
            }
        });

        //关心结果的事务执行方式
        Student student = transactionTemplate.execute(new TransactionCallback<Student>() {
            @Override
            public Student doInTransaction(TransactionStatus status) {
                try {
                    //db操作
                    Student student = doSomething();
                    return student;
                } catch (Exception e) {
                    //回滚
                    status.isRollbackOnly();
                    e.printStackTrace();
                }
                return null;
            }
        });
    }

    public Student doSomething() {
        return new Student();
    }

    //使用PlatformTransactionManager
    @Autowired
    private PlatformTransactionManager transactionManager;


    public void testPlatformTransactionManager() {
        // 开启事务
        DefaultTransactionDefinition tdfDefinition = new DefaultTransactionDefinition();
        //隔离级别,-1表示使用数据库默认级别
        // tdfDefinition.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);
        // 事务传播行为
        // tdfDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

        TransactionStatus status = transactionManager.getTransaction(tdfDefinition);
        try {
            // db操作
            // 提交
            transactionManager.commit(status);
        } catch (Exception e) {
            //回滚
            transactionManager.rollback(status);
            e.printStackTrace();
        }
    }

    //使用SqlSession方式
    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    public void testSqlSessionFactory() {
        SqlSession sqlSession = null;
        try {
            sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH, true);
            // db操作
            sqlSession.commit();
        } catch (Exception e) {
            if (null != sqlSessionFactory) {
                //回滚
                sqlSession.rollback(true);
                e.printStackTrace();
            }
        } finally {
            if (null != sqlSession) {
                sqlSession.clearCache();
                sqlSession.close();
            }
        }
    }


}
