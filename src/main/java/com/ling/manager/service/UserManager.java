package com.ling.manager.service;

import org.springframework.stereotype.Component;

/**
 * Created by TianHeLing on 2018/10/16.  总接口：全部接口方法在这里定义
 */
@Component
public interface UserManager {

    //如果该方法是共用方法，可以在这里写具体业务处理
    default String getName(){
        return "";
    };

    default String getAge(){
        return "";
    };

}
