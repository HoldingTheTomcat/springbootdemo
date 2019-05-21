package com.ling.testMain;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author TianHeLing
 * @Description
 * @date 2019/5/21
 */
public class ShiroIniTest {

    /**
     * 真正对的认证信息：用户名、密码 存在ini文件里面
     */
    @Test
    public void testShiroIni() {
        IniSecurityManagerFactory factory = new IniSecurityManagerFactory("classpath:shiro.ini");

        // 从工厂中获权SecuritManager实剑
        SecurityManager manager = factory.getInstance();
        // 设置当前运行环境
        SecurityUtils.setSecurityManager(manager);
        //创建subject（当前用户）
        Subject subject = SecurityUtils.getSubject();
        // 创建令牌
        UsernamePasswordToken token = new UsernamePasswordToken("helen", "123456");
        // 登录
        subject.login(token);
        // 验证是否登录
        boolean authenticated = subject.isAuthenticated();
        System.out.println("authenticated:" + authenticated);

    }

    /**
     * 自定义realm,从realm中获取：真正对的的用户信息,即用户名、密码在realm中获取,并交给manager
     */
    @Test
    public void testShiroRealm() {
        IniSecurityManagerFactory factory = new IniSecurityManagerFactory("classpath:shiro_realm.ini");

        // 从工厂中获权SecuritManager实剑
        SecurityManager manager = factory.getInstance();
        // 设置当前运行环境
        SecurityUtils.setSecurityManager(manager);
        //创建subject（当前用户）
        Subject subject = SecurityUtils.getSubject();
        // 创建令牌 ,注意：这里密码存的时候,是按char[]数组存的,所以取得时候也要按char[]数组取,用户名是按字符串存的
        UsernamePasswordToken token = new UsernamePasswordToken("helen", "1234562");
        // 登录,走到这一步：会调ream里面的认证方法
        subject.login(token);
        // 验证是否登录
        boolean authenticated = subject.isAuthenticated();
        System.out.println("authenticated:" + authenticated);

    }

    @Test
    public void testSalt() {

        //用户名
        String username = "helen";
        //原始密码
        String source = "123456";
        //加密方式
        String type = "md5";
        //盐
        String salt = username;
        // 加密次数
        int count = 1024;
        SimpleHash md5 = new SimpleHash(type,source,salt,count);
        System.out.println(md5);
    }

    /**
     * 自定义realm,从realm中获取：真正对的的用户信息,即用户名、密码在realm中获取,并交给manager
     */
    @Test
    public void testShiroSalt() {
        IniSecurityManagerFactory factory = new IniSecurityManagerFactory("classpath:shiro_Md5Salt.ini");

        // 从工厂中获权SecuritManager实剑
        SecurityManager manager = factory.getInstance();
        // 设置当前运行环境
        SecurityUtils.setSecurityManager(manager);
        //创建subject（当前用户）
        Subject subject = SecurityUtils.getSubject();
        // 创建令牌 ,注意：这里密码存的时候,是按char[]数组存的,所以取得时候也要按char[]数组取,用户名是按字符串存的
        UsernamePasswordToken token = new UsernamePasswordToken("helen", "123456");
        // 登录,走到这一步：会调ream里面的认证方法
        subject.login(token);
        // 验证是否登录
        boolean authenticated = subject.isAuthenticated();
        System.out.println("authenticated:" + authenticated);

    }
    
    /**
     * 授权：权限信息在ini配置文件里
     */
    @Test
    public void testshiroAuthorization() {
        IniSecurityManagerFactory factory = new IniSecurityManagerFactory("classpath:shiro_authorization.ini");

        // 从工厂中获权SecuritManager实剑
        SecurityManager manager = factory.getInstance();
        // 设置当前运行环境
        SecurityUtils.setSecurityManager(manager);
        //创建subject（当前用户）
        Subject subject = SecurityUtils.getSubject();
        // 创建令牌 ,注意：这里密码存的时候,是按char[]数组存的,所以取得时候也要按char[]数组取,用户名是按字符串存的
        UsernamePasswordToken token = new UsernamePasswordToken("helen", "123456");
        // 登录,走到这一步：会调ream里面的认证方法
        subject.login(token);
        // 验证是否登录
        boolean authenticated = subject.isAuthenticated();
        System.out.println("authenticated:" + authenticated);

        // 角色判断
        
        boolean isHashRole = subject.hasRole("role1");
        System.out.println("单个角色判断："+ isHashRole);
        boolean[] booleans = subject.hasRoles(Arrays.asList("role1", "role2"));
        for (boolean aBoolean : booleans) {
            System.out.println(aBoolean);
        }
        boolean isHasAllRoles = subject.hasAllRoles(Arrays.asList("role1", "role2"));
        System.out.println("多个角色判断：" + isHasAllRoles);
        
        //所有的check操作并没有返回值， 不满足时，直接抛出异常
        subject.checkRole("role3");
        
        //权限判断
        boolean permitted = subject.isPermitted("system:user:create");
        System.out.println("单个权限判断:"+permitted);
        boolean permittedAll = subject.isPermittedAll("system:user:create", "system:config:delete");
        System.out.println("多个权限判断："+permittedAll);

    }


    /**
     * 授权：权限信息在自定义realm中
     */
    @Test
    public void testshiroAuthorizationRealm() {
        IniSecurityManagerFactory factory = new IniSecurityManagerFactory("classpath:shiro_authorization_realm.ini");

        // 从工厂中获权SecuritManager实剑
        SecurityManager manager = factory.getInstance();
        // 设置当前运行环境
        SecurityUtils.setSecurityManager(manager);
        //创建subject（当前用户）
        Subject subject = SecurityUtils.getSubject();
        // 创建令牌 ,注意：这里密码存的时候,是按char[]数组存的,所以取得时候也要按char[]数组取,用户名是按字符串存的
        UsernamePasswordToken token = new UsernamePasswordToken("helen", "123456");
        // 登录,走到这一步：会调ream里面的认证方法
        subject.login(token);
        // 验证是否登录
        boolean authenticated = subject.isAuthenticated();
        System.out.println("authenticated:" + authenticated);

        // 角色判断

        boolean isHashRole = subject.hasRole("role1");
        System.out.println("单个角色判断：" + isHashRole);
        boolean[] booleans = subject.hasRoles(Arrays.asList("role1", "role2"));
        for (boolean aBoolean : booleans) {
            System.out.println(aBoolean);
        }
        boolean isHasAllRoles = subject.hasAllRoles(Arrays.asList("role1", "role2"));
        System.out.println("多个角色判断：" + isHasAllRoles);

        //所有的check操作并没有返回值， 不满足时，直接抛出异常
        subject.checkRole("role3");

        //权限判断
        boolean permitted = subject.isPermitted("system:user:create");
        System.out.println("单个权限判断:" + permitted);
        boolean permittedAll = subject.isPermittedAll("system:user:create", "system:config:delete");
        System.out.println("多个权限判断：" + permittedAll);

    }

}
