package com.ling.manager.abstractimpl;

import com.ling.manager.facade.UserManagerFacade;
import com.ling.manager.service.UserManager;

/**
 * @author TianHeLing
 * @Description 公用接口方法 + 抽象接口方法
 * @date 2019/3/18
 */
public abstract class UserManagerAbstract implements UserManager {

    public UserManagerAbstract() {
        regist();
    }

    protected abstract Integer getChannelId();

    //把具体实现注册到容器内
    protected void regist() {
        UserManagerFacade.regist(getChannelId(), this);
    }


    /**
     * 注意：
     * UserManager所有接口方法实现如下，先空实现，然后去UserManagerAbstract具体实现里面重写
     * 通道公用方法可以直接在这里实现，不是公用方法，去UserManagerAbstract具体实现里面写
     */
    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getAge() {
        return null;
    }
}
