package com.ling;

import com.alibaba.fastjson.JSON;
import com.ling.testMain.Const;
import com.ling.testMain.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * @author TianHeLing
 * @Description
 * @date 2018/12/14
 */
public class TestMain {

    private static Logger logger = LoggerFactory.getLogger(TestMain.class);
     
 
    
    
    public static void main(String[] args) {
        Student student = new Student();
        System.out.println(Const.PI);

    }
    
   

    public static String changeCharset(String str, String oldCharset, String newCharset) {
        try {
            return new String(str.getBytes(oldCharset), newCharset);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    

    /**
     * 判断一个对象是否是基本类型或基本类型的封装类型
     */
    private static  boolean isPrimitive(Object obj) {
        try {
            return ((Class<?>) obj.getClass().getField("TYPE").get(null)).isPrimitive();
        } catch (Exception e) {
            System.out.println("异常");
            return false;
        }
    }

    public static void getName(String name, Integer age) {
        System.out.println(2);
        return;
    }

}
