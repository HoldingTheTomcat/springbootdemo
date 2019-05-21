package com.ling.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.AuthenticatingRealm;

/**
 * @author TianHeLing
 * @Description 手动获取用户的真正账号、密码信息
 * @date 2019/5/21
 */
public class Realm01 extends AuthenticatingRealm {
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 假设数帮库中获权出来的用户名和密码是
        String username = "helen";
        String password = "123456";

        // 获得输入的用户
        String inputUserName = (String) authenticationToken.getPrincipal();
        // 手动判断: 数据库查询到的用户名和输入的用户名是否相等
        if (!inputUserName.equals(username)){
            throw new UnknownAccountException("用户名不存在");
        }
        // 手动判断密码
        String inputUserPassword = new String((char[]) authenticationToken.getCredentials());
        if (!inputUserPassword.equals(password)){
            throw new IncorrectCredentialsException("密码不正确");
        }
        
        // 另外一种方式：取inputName、inputPassword
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String inputPassword = new String(token.getPassword());
        String inputName = token.getUsername();
        System.out.println("inputName:" + inputName + ',' + "password:" + inputPassword);
        
        //由框架自动去对比：用户名、密码 ，所以以上的手动判断，全都不需要
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username, password, this.getName());
        return info;
    }
}
