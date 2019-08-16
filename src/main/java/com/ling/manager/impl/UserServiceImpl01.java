package com.ling.manager.impl;

import com.ling.manager.enums.ChannelTypeEum;
import com.ling.manager.service.UserManager;
import org.springframework.stereotype.Service;

/**
 * @author TianHeLing
 * @Description
 * @date 2019/3/18
 */
@Service
public class UserServiceImpl01 implements UserManager {
    @Override
    public Integer getChannelId() {
        return ChannelTypeEum.YxChannel.getCode();
    }

    @Override
    public String getName() {
        return "01name";
    }
}
