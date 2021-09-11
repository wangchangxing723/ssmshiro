package com.zking.ssm.shiro;

import com.zking.ssm.model.User;
import com.zking.ssm.service.IUserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private IUserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String userName = principalCollection.getPrimaryPrincipal().toString();
        //验证用户的角色(先知道用户的角色)
        Set<String> roles = userService.getRole(userName);
        //获取用户的权限
        Set<String> permissions = userService.getPermission(userName);
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
        info.setRoles(roles);
        info.setStringPermissions(permissions);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = authenticationToken.getPrincipal().toString();
        String password = authenticationToken.getCredentials().toString();
        User user=new User();
        user.setUsername(username);
        user.setPassword(password);
        User u = userService.login(user);
        if (null==u){
            throw new RuntimeException("账号不存在");
        }
        //将找到的数据封装到身份验证信息类中
        SimpleAuthenticationInfo authenticationInfo=new SimpleAuthenticationInfo(u.getUsername(),u.getPassword(), ByteSource.Util.bytes(u.getSalt()),this.getName());

        return authenticationInfo;
    }
}
