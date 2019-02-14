package com.ling;

/**
 * @author TianHeLing
 * @Description
 * @date 2018/12/14
 */
public class TestMain {

    
    public static void main(String[] args) {
       
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
