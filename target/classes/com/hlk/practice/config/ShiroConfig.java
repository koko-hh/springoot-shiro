package com.hlk.practice.config;
import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.hlk.practice.shiro.Realm;
import com.hlk.practice.shiro.rolesFilter;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
    /**
     * 常见过滤器：
     * anon：无需认证（登录）可以访问
     * authc：必须认证才可以访问
     * user:如果使用Remember Me的功能，可以直接访问
     * perms:该资源必须得到资源权限才可以访问
     * role:该资源必须得到角色权限才可以访问
     */


    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager")DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean =new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

/*        Map filtersMap = new LinkedHashMap<>();
        filtersMap.put("rolesFilter",new rolesFilter());
        shiroFilterFactoryBean.setFilters(filtersMap);//使用自定义fitter*/

        Map<String, String> filterMap=new LinkedHashMap<String, String>();
        filterMap.put("/allList.html", "perms[p1]");
        filterMap.put("/allPeople.html", "perms[p2]");
        filterMap.put("/modify.html", "perms[p3]");
        filterMap.put("/login.html", "anon");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        shiroFilterFactoryBean.setLoginUrl("/login.html");
        shiroFilterFactoryBean.setUnauthorizedUrl("/unAuth.html");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        return shiroFilterFactoryBean;
    }


    @Bean(name="securityManager")
    public DefaultWebSecurityManager getdefaultDefaultWebSecurityManager(@Qualifier("Realm")Realm Realm){
        DefaultWebSecurityManager securityManager=new DefaultWebSecurityManager();
        securityManager.setRealm(Realm);
        return securityManager;
    }

    @Bean(name="Realm")
    public Realm getRealm(){
        return new Realm();
    }

    @Bean
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }
}
