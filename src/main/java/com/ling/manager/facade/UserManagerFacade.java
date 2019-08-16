package com.ling.manager.facade;

import com.ling.manager.service.UserManager;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author TianHeLing
 * @Description 由于我们需要使用0、1、2...表示这个接口的不同实现类，
 *              所以多个接口，我们就需要多个ManagerFacade，每个ManagerFacade表示一个接口的实现类的容器
 *              所以没法把ManagerFacade抽取成一个公共的
 * @date 2019/3/18
 */
@Component
public class UserManagerFacade {

    //静态变量没法定义为泛型
    private static Map<Integer, UserManager> CHANNEL_MAP = new HashMap<>();

    /**
     * 因为这里是静态方法，要访问CHANNEL_MAP属性，所以CHANNEL_MAP必须定义为静态属性
     * @param channelId
     * @param channelManager
     */
    public static void register(Integer channelId, UserManager channelManager) {
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
