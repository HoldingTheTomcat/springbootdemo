package com.ling.manager.facade;

import com.ling.manager.service.UserManager;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author TianHeLing
 * @Description
 * @date 2019/3/18
 */
@Component
public class UserManagerFacade {

    private static Map<Integer, UserManager> CHANNEL_MAP = new HashMap<>();

    public static void regist(Integer channelId, UserManager channelManager) {
        CHANNEL_MAP.put(channelId, channelManager);
    }

    public UserManager getChanel(Integer channelId) {
        UserManager channelManager = CHANNEL_MAP.get(channelId);
        if (Objects.isNull(channelManager)) {
            throw new IllegalArgumentException("illegal channelId");
        }
        return channelManager;
    }
}
