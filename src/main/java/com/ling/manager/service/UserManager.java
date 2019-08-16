package com.ling.manager.service;


import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by TianHeLing on 2018/10/16.  总接口：全部接口方法在这里定义
 */
public interface UserManager {

    //静态变量没法定义为泛型
    Map<Integer, UserManager> CHANNEL_MAP = new HashMap<>();

    /**
     * 因为这里是静态方法，要访问CHANNEL_MAP属性，所以CHANNEL_MAP必须定义为静态属性
     *
     * @param channelId
     * @param channelManager
     */
    default void register(Integer channelId, UserManager channelManager) {
        CHANNEL_MAP.put(channelId, channelManager);
    }

    static UserManager getChanel(Integer channelId) {
        UserManager channelManager = CHANNEL_MAP.get(channelId);
        if (Objects.isNull(channelManager)) {
            throw new IllegalArgumentException("illegal channelId");
        }
        return channelManager;
    }

    Integer getChannelId();

    /**
     * jDK8 接口新增默认方法: 所以我们不需要再创建抽象类，通过构造函数注册ChanelId,也不用指定公共方法
     * PostConstruct：spring声明周期函数，实体类创建时执行
     * 这里的执行顺序是 实体类创建->PostConstruct指定的初始化方法
     * 所以这里的方法能拿到具体实现类
     */
    @PostConstruct
    default void registerChanel() {
        this.register(this.getChannelId(), this);
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
