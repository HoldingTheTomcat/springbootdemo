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
public class UserServiceImpl02 implements UserManager {
    @Override
    public Integer getChannelId() {
        return ChannelTypeEum.WmChannel.getCode();
    }

    @Override
    public String getName() {
        return "02name";
    }
}
