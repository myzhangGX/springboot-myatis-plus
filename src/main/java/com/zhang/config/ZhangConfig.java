package com.zhang.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ZhangConfig implements WebMvcConfigurer {
  /**
   * @param registry
   */
  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
    registry.addViewController("/").setViewName("longin");
    registry.addViewController("/index.html").setViewName("indexk");
    registry.addViewController("/longin.html").setViewName("longin");
  }

  /*mybatai-plus分页*/

  /**
   * @return
   */
  @Bean
  public MybatisPlusInterceptor mybatisPlusInterceptor() {
    MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
    interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
    return interceptor;
  }


  @Bean
  public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("getDefaultWebSecurityManager") DefaultWebSecurityManager defaultWebSecurityManager) {
    ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
    /*//设计安全管理器*/
    bean.setSecurityManager(defaultWebSecurityManager);
    /*添加shiro的内置过滤器
     * onon：无需认证就可以访问
     * outhc：必须认证了才能访问
     * user 必须拥有记住我 功能 才能访问
     * parms 拥有某个资源权限才能访问
     * role  拥有某个角色权限才可以访问
     * */

    Map<String, String> filterMap = new LinkedHashMap<>();
    //* filterMap.put("/user/add", "authc");
    filterMap.put("redirect:/index.html", "authc");//*
    //*授权 正常情况下，没有授权会跳转到为授权页面*//*

    bean.setFilterChainDefinitionMap(filterMap);

    /*设置登录的请求*/
    bean.setLoginUrl("/longin");
    return bean;
  }


  //2. DefaultWebSecurityManager
  @Bean
  public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm) {
    DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
    //关联UserRealm
    securityManager.setRealm(userRealm);

    return securityManager;
  }

  //创建 realm对象 需要自定义类
  @Bean  //(name = "userRealm")
  public UserRealm userRealm() {
    return new UserRealm();
  }


  /*整合ShiroDialect ： 用来整合shirothymeleaf*/
  @Bean
  public ShiroDialect getShiroDaialect() {
    return new ShiroDialect();
  }


}
