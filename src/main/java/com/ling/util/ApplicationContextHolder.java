package com.ling.util;

import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 1、实现了ApplicationContextAware这个接口的bean，当spring容器初始化的时候，会自动调用setApplicationContext()方法
 * 将ApplicationContext注入进来
 * 2、之所以方法类能够灵活自如地获取ApplicationContext，就是因为spring能够为我们自动地执行了setApplicationContext。
 * 3、但是，spring不会无缘无故地为某个类执行它的方法的，所以
 * 就很有必要通过注册方法类的方式告知spring有这样子一个类的存在,这里我们使用@Component来进行注册
 */
@Component
public class ApplicationContextHolder implements ApplicationContextAware, DisposableBean {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationContextHolder.class);

    private static ApplicationContext applicationContext;

    /**
     * 实现 ApplicationContextAware 接口，注入 Context 到静态变量中
     *
     * @param applicationContext
     * @throws BeansException
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationContextHolder.applicationContext = applicationContext;
    }

    /**
     * 实现 DisposableBean 接口，在 Context 关闭时清理静态变量
     *
     * @throws Exception
     */
    @Override
    public void destroy() throws Exception {
        logger.debug("清除 SpringContext 中的 ApplicationContext: {}", applicationContext);
        applicationContext = null;
    }


    /**
     * 获取存储在静态变量中的 ApplicationContext
     *
     * @return
     */
    public static ApplicationContext getApplicationContext() {
        assertContextInjected();
        return applicationContext;
    }

    /**
     * 从静态变量 applicationContext 中获取 Bean，自动转型成所赋值对象的类型
     *
     * @param name
     * @param <T>
     * @return
     */
    public static <T> T getBean(String name) {
        assertContextInjected();
        return (T) applicationContext.getBean(name);
    }

    /**
     * 从静态变量 applicationContext 中获取 Bean，自动转型成所赋值对象的类型，测试提交
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getBean(Class<T> clazz) {
        assertContextInjected();
        return applicationContext.getBean(clazz);
    }


    /**
     * 断言 Context 已经注入
     */
    private static void assertContextInjected() {
        Validate.validState(applicationContext != null, "applicationContext 属性未注入，请在 spring-context.xml 配置中定义 ApplicationContextHolder");
    }
}
