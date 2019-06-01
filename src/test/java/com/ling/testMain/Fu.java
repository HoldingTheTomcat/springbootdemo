package com.ling.testMain;

/**
 * @author TianHeLing
 * @Description
 * @date 2019/5/12
 */
public class Fu {

    public static A a = new A();

    

    static {
        System.out.println(Fu.aaa);
        System.out.println("c");
    }

    public static int aaa = 10;

    {
        System.out.println("d");
    }

    public Fu() {
        System.out.println("e");
    }

}
