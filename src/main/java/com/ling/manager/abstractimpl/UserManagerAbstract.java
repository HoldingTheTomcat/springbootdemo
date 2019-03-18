package com.ling.manager.abstractimpl;

import com.ling.manager.facade.UserManagerFacade;
import com.ling.manager.service.UserManager;

/**
 * @author TianHeLing
 * @Description 抽象类UserManagerAbstract实现接口UserManager的目的是：实现类初始化的时候，完成注入操作
 *                                                                    因为接口不能使用构造方法注入
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
     * 注意： 接口UserManager的方法去UserManagerAbstract的子类里面重写自己的即可
     */
    

}
