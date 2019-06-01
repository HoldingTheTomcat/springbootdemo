package com.ling.testMain;

/**
 * @author TianHeLing
 * @Description
 * @date 2019/5/12
 */
public class Student {

    static {
        //	System.out.println(num);//编译错误
        //	num = 10;//OK的
        fun();
    }

    public static int num = 10;

    public static void fun() {
        System.out.println(num);
    }

}
