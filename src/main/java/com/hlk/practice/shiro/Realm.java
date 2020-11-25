package com.hlk.practice.shiro;

import com.hlk.practice.mapper.PeopleMapper;
import com.hlk.practice.pojo.People;
import com.hlk.practice.service.PeopleService;
import org.apache.catalina.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

public class Realm extends AuthorizingRealm {
    @Autowired
    PeopleMapper peopleMapper;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行授权逻辑");

        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
        Subject subject= SecurityUtils.getSubject();
        People people=(People)subject.getPrincipal();
        People dbpeople = peopleMapper.getPeople(people.getId());
        info.addStringPermission(dbpeople.getRole());
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行认证逻辑");
        // 编写shiro判断逻辑，判断用户名和密码
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        People people = peopleMapper.getPeopleByName(token.getUsername());
        System.out.println(people);
        if (people == null) {
            return null;//shiro底层会抛出UnknownAccountException
        }
        // 2. 判断密码
        // 参数1：需要返回给login方法的数据；参数2：数据库密码，shiro会自动判断
        return new SimpleAuthenticationInfo(people, people.getPwd(), "");
    }
}
