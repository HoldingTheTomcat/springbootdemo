package com.ling.testMain;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * @author TianHeLing
 * @Description
 * @date 2019/5/12
 */
public class main2 {


    public static void main(String[] args) {
        Md5Hash md5Hash = new Md5Hash("000000", "ling", 1024);
        System.out.println(md5Hash.toString());
    }

}
