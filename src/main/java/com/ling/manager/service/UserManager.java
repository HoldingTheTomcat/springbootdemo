package com.ling.manager.service;

import com.ling.manager.facade.UserManagerFacade;

import javax.annotation.PostConstruct;

/**
 * Created by TianHeLing on 2018/10/16.  总接口：全部接口方法在这里定义
 */
public interface UserManager {
    Integer getChannelId();

    /**
     * jDK8 接口新增默认方法: 所以我们不需要再创建抽象类，通过构造函数注册ChanelId,也不用指定公共方法
     * PostConstruct：spring声明周期函数，实体类创建时执行
     * 这里的执行顺序是 实体类创建->PostConstruct指定的初始化方法
     * 所以这里的方法能拿到具体实现类
     */
    @PostConstruct
    default void registerChanel() {
        UserManagerFacade.register(this.getChannelId(), this);
    }

    //如果该方法是共用方法，可以在这里写具体业务处理
    default String getName() {
        return "";
    }

    ;

    default String getAge() {
        return "";
    }
}
