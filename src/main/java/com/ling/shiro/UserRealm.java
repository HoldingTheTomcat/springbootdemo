package com.ling.shiro;

import com.ling.dao.entity.SysUserEntity;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Component;

/**
 * @author TianHeLing
 * @Description
 * @date 2019/6/1
 */
@Component
public class UserRealm  extends AuthorizingRealm {

    /**
     * 认证： 先认证再授权
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
       
        // 获取输入的账号、密码
        String inputUsername = (String) token.getPrincipal();
        String inputPassword = new String((char[]) token.getCredentials());

        // 去数据库查询用户
        // SysUserEntity user = sysUserService.queryByUserName(inputUsername);
        SysUserEntity user = new SysUserEntity();
        user.setPassword("123");
        user.setUsername("lisi");
        user.setStatus(1);
        String password = user.getPassword();
        String nameSalt = user.getUsername();

        ByteSource bytes = ByteSource.Util.bytes(nameSalt);

        // 需要手动判断：账号是否存在，因为SimpleAuthenticationInfo只会比较密码，并不校验用户名是否正确，用户名是否存在
        if (user == null) {
            throw new UnknownAccountException("账号或密码不正确");
        }

        // 不需要手动判断密码
        // 密码错误
        // if (!password.equals(user.getPassword())) {
        //     throw new IncorrectCredentialsException("账号或密码不正确");
        // }

        //账号锁定
        if (user.getStatus() == 0) {
            throw new LockedAccountException("账号已被锁定,请联系管理员");
        }

        // 第一个参数并不是uername
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, password, bytes, getName());
        return info;
    }

    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }
}
