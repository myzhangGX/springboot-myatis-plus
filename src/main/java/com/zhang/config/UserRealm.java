package com.zhang.config;


import com.zhang.pojo.User;
import com.zhang.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

//自定义的 UserRealm extends AuthorizingRealm
public class UserRealm extends AuthorizingRealm {
  @Autowired
  UserService userService;

  //授权
  @Override
  protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
    System.out.println("执行了=>授权doGetAuthorizationInfo");
    /*SimpleAuthorizationInfo*/
    SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

    /* info.addStringPermission("user:add");*/

    /*拿到当前用户的登录对象*/
    Subject subject = SecurityUtils.getSubject();
    User currentUser = (User) subject.getPrincipal();
    //拿到Use的对象

    /*  *//*设置当前用户的权限*//*
        info.addStringPermission(currentUser.getUsername());.getSex():*/


    return info;
  }

  //认证
  @Override
  protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
    System.out.println("执行了=>认证doGetAuthorizationInfo");
    //认证
    /*用户名，密码 数据库中获取*/

    /*链接真实数据库*/
    UsernamePasswordToken userToken = (UsernamePasswordToken) token;

    User user = userService.Userquerdernyname(userToken.getUsername());

    if (user == null) {//没有这个人
      return null;
    }
    /*把密码放入进去SimpleAuthenticationInfo("", user.getAddress(), "");*/
    /*密码认证 shiro 做*/
    /*可以加密 ： md5 md5盐值加密*/
    return new SimpleAuthenticationInfo(user, user.getPassword(), "");

  }
}
