package com.ling.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.ArrayList;
import java.util.List;

/**
 * @author TianHeLing
 * @Description 先执行认证，再执行授权，授权时从数据库获得角色、权限信息
 * @date 2019/5/22
 */
public class ReamAuthoriation  extends AuthorizingRealm {
    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String userName = (String)principalCollection.getPrimaryPrincipal();
        System.out.println("用户名:"+userName);
        
        //从数据库获取权限
        List<String> permessions = new ArrayList<>();
        permessions.add("system:user:*");
        permessions.add("system:menu:*");
        permessions.add("system:config:*");
        
        //从数据库获取角色
        List<String> roles = new ArrayList<>();
        roles.add("role1");
        roles.add("role2");
        roles.add("role3");

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addStringPermissions(permessions);
        simpleAuthorizationInfo.addRoles(roles);
        return simpleAuthorizationInfo;
    }

    /**
     * 认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 假设数帮库中获权出来的用户名和密码是
        String username = "helen";
        String password = "123456";

        //由框架自动去对比：用户名、密码 ，所以以上的手动判断，全都不需要
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username, password, this.getName());
        return info;
    }
}
