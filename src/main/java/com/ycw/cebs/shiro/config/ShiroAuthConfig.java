package com.ycw.cebs.shiro.config;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.Cookie;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import com.geeorange.cbbs.shiro.realm.UserRealm;
import com.ycw.cebs.shiro.filter.LocalFormAuthenticationFilter;

@Configuration
public class ShiroAuthConfig {

    @Bean("sessionIdCookie")
    public Cookie sessionIdCookie() {
        SimpleCookie cookie = new SimpleCookie();
        cookie.setHttpOnly(true);
        cookie.setMaxAge(-1);
        cookie.setName("jsessionid");
        cookie.setPath("/");
        return cookie;
    }

    /**
     * <p>shiro 会话管理</p>
     */
//    @Bean("sessionManager")
//    public SessionManager sessionManager() {
//    	RestAndWebSessionManager webSessionManager = new RestAndWebSessionManager();
//        webSessionManager.setSessionIdCookie(sessionIdCookie());
//        webSessionManager.setSessionDAO(redisSessionDAO());
//        return webSessionManager;
//    }
//
//    @Bean
//    public RedisSessionDAO redisSessionDAO() {
//    	RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
//    	redisSessionDAO.setRedisManager(redisManager());
//    	return redisSessionDAO;
//    }

//    @Bean
//	public RedisManager redisManager() {
//		RedisManager redisManager = new RedisManager();
//		redisManager.setHost(redisManagerProperties().getHost() + ":" + redisManagerProperties().getPort());
//		redisManager.setPassword(redisManagerProperties().getPassword());
//		redisManager.setDatabase(redisManagerProperties().getDatabase());
//		return redisManager;
//	}
//
//	@Bean
//	@ConfigurationProperties(prefix = "spring.redis")
//	public RedisManagerProperties redisManagerProperties() {
//		return new RedisManagerProperties();
//	}
//
//    @Bean
//    public RedisCacheManager redisCacheManager() {
//    	RedisCacheManager redisCacheManager = new RedisCacheManager();
//        redisCacheManager.setRedisManager(redisManager());
//        redisCacheManager.setPrincipalIdFieldName("id");
//        return redisCacheManager;
//    }

    /**
     * <p>配置核心安全事务管理器</p>
     * <p>如果有多个relam可以在此处扩展，
     * 并通过  ModularRealmAuthenticator(securityManager.authenticator) 指定认证策略(默认FirstSuccessfulStrategy) </p>
     */
    @DependsOn("userRealm")
    @Bean(name = "securityManager")
	public SecurityManager securityManager(@Qualifier("userRealm") UserRealm authRealm) {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
//		securityManager.setCacheManager(redisCacheManager());
		securityManager.setRealm(authRealm);
//		securityManager.setSessionManager(sessionManager());
		return securityManager;
	}

    /**
     * <p>ShiroFilter</p>
     * <p>直接使用ShiroFilterFactoryBean，无法自己定义UrlPattern，默认拦截 /*</p>
     * <p>如需定义拦裁，通过 FilterRegistrationBean 去注册一个 DelegatingFilterProxy</p>
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("securityManager") SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(securityManager);
        // 配置登录的URI
        shiroFilter.setUnauthorizedUrl("/sys/unauthorized");
        //配置访问权限
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        filterChainDefinitionMap.put("/sys/login", "anon"); // 表示可以匿名访问
        filterChainDefinitionMap.put("/druid/**", "anon");
        filterChainDefinitionMap.put("/actuator/**", "anon");
        filterChainDefinitionMap.put("/sys/captcha/pre", "anon");
        filterChainDefinitionMap.put("/sys/captcha/check", "anon");
        filterChainDefinitionMap.put("/code.html", "anon");
        filterChainDefinitionMap.put("/rsa.html", "anon");
        filterChainDefinitionMap.put("/sys/rsa/generate", "anon");
        filterChainDefinitionMap.put("/sys/logo/get", "anon");// 登录页信息获取
        filterChainDefinitionMap.put("/sys/app/version/download/**", "anon");// 下载app安装包文件
        filterChainDefinitionMap.put("/ins/policy/yian/pay/callback", "anon");
        filterChainDefinitionMap.put("/ins/policy/btxl/callback", "anon");
        filterChainDefinitionMap.put("/**", "authc"); // 表示需要认证才可以访问
        Map<String, Filter> filters = new LinkedHashMap<String, Filter>();
        filters.put("authc", new LocalFormAuthenticationFilter());
        shiroFilter.setFilters(filters);

        shiroFilter.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilter;
    }

    /**
     * 开启 Shiro Spring AOP 权限注解的支持
     */
    @Bean("lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @DependsOn("lifecycleBeanPostProcessor")
    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
        creator.setProxyTargetClass(true);
        return creator;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(
            @Qualifier("securityManager") SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

//    @Bean("credentialsMatcher")
//    public RetryLimitHashedCredentialsMatcher retryLimitHashedCredentialsMatcher(){
//        RetryLimitHashedCredentialsMatcher retryLimitHashedCredentialsMatcher = new RetryLimitHashedCredentialsMatcher();
//        return retryLimitHashedCredentialsMatcher;
//    }
}
