package com.ling.config;

import com.ling.shiro.UserRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.Cookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author TianHeLing
 * @Description
 * @date 2019/6/1
 */

@Configuration
public class ShiroConfig {


    @Bean
    public SessionManager sessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        //设置session过期时间为1小时(单位：毫秒)，默认为30分钟
        sessionManager.setGlobalSessionTimeout(60 * 60 * 1000);
        //扫描session线程，清理过期会话
        sessionManager.setSessionValidationSchedulerEnabled(true);
        //禁止url栏拼接sessionid，作用是：如果浏览器禁用cookie,是否把cookie拼接到url后面
        sessionManager.setSessionIdUrlRewritingEnabled(false);
        return sessionManager;
    }

    //带有Bean注解的方法：方法的参数相当于传入spring容器中创建的对象，Autowired
    @Bean
    public SecurityManager securityManager(UserRealm userRealm, SessionManager sessionManager) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);
        securityManager.setSessionManager(sessionManager);

        //加密、加盐
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName("md5");
        matcher.setHashIterations(1024);
        //已过期：已经不需要指定加盐，因为只要返回带盐的SimpleAuthenticationInfo就行了
        // matcher.setHashSalted(true);
        userRealm.setCredentialsMatcher(matcher);
        
        
        //cookie:记住我相关设置
        CookieRememberMeManager rememberMeManager = new CookieRememberMeManager();
        Cookie cookie = rememberMeManager.getCookie();
        cookie.setMaxAge(60 * 60 * 24 * 30);
        securityManager.setRememberMeManager(rememberMeManager);

        return securityManager;
    }

    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(securityManager);
        
        //登录页配置，如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        shiroFilter.setLoginUrl("/login.html"); 
        //认证成功跳转到主页
        shiroFilter.setSuccessUrl("/index.html");
        //没有权限页面,如果没登录，那么连未授权提示页面都看不到
        shiroFilter.setUnauthorizedUrl("unauthorized.html"); 

        //为了保证过滤的优先顺序，所以使用LinkedHashMap
        Map<String, String> filterMap = new LinkedHashMap<>();
        //什么都不做直接放行，注意:自己的业务js不要设置成anon
        filterMap.put("/public/**", "anon"); 
        filterMap.put("/login.html", "anon");
        filterMap.put("/sys/login", "anon");
        filterMap.put("/captcha.jpg", "anon");

        filterMap.put("/webjars/**", "anon");
        filterMap.put("/api/**", "anon");
        //swagger配置
        filterMap.put("/swagger**", "anon");
        filterMap.put("/v2/api-docs", "anon");
        filterMap.put("/swagger-resources/configuration/ui", "anon");
        
        //选中记住我能访问的资源，可以设置指定路径下的资源，做为记住我能访问
        filterMap.put("/**", "user");
        //登录后才能访问，他实际是shiro内部的一个拦截器，authc:不支持记住我功能
        filterMap.put("/**", "authc");
      
        shiroFilter.setFilterChainDefinitionMap(filterMap);

        return shiroFilter;
    }

    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator proxyCreator = new DefaultAdvisorAutoProxyCreator();
        proxyCreator.setProxyTargetClass(true);
        return proxyCreator;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }
}
