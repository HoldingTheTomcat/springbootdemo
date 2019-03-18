package com.ling.manager.impl;

import com.ling.manager.abstractimpl.UserManagerAbstract;
import com.ling.manager.enums.ChannelTypeEum;
import org.springframework.stereotype.Service;

/**
 * @author TianHeLing
 * @Description
 * @date 2019/3/18
 */
@Service
public class UserServiceImpl01 extends UserManagerAbstract {
    @Override
    protected Integer getChannelId() {
        return ChannelTypeEum.YxChannel.getCode();
    }

    @Override
    public String getName() {
        return "01name";
    }
}
