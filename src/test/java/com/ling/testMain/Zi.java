package com.ling.testMain;

/**
 * @author TianHeLing
 * @Description
 * @date 2019/5/12
 */
public class Zi extends Fu {

    public static B b = new B();

    static {
        System.out.println("f");
    }

    {
        System.out.println("g");
    }

    public Zi() {
        System.out.println("h");
    }

}
