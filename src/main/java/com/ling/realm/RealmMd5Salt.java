package com.ling.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.util.ByteSource;

/**
 * @author TianHeLing
 * @Description 手动获取用户的真正账号、密码信息+，加密+加盐匹配
 * @date 2019/5/21
 */
public class RealmMd5Salt extends AuthenticatingRealm {
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        
        // 假设数帮库中获权出来的用户名和密码是
        String username = "helen";
        // String password = "250d6bc11dd9015cc6557a1e56741002";
        String password = new SimpleHash("MD5", "123456", username, 1024).toString();
        String salt = username;

        ByteSource byteSalt = ByteSource.Util.bytes(salt);
        //由框架自动去对比：用户名、密码
        //注意：这里多了盐byte
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username, password, byteSalt,this.getName());
        return info;
    }
}
