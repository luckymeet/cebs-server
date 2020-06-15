package com.ycw.cebs.shiro.config;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.Cookie;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import com.ycw.cebs.common.config.RedisManagerProperties;
import com.ycw.cebs.shiro.filter.LocalFormAuthenticationFilter;
import com.ycw.cebs.shiro.realm.UserRealm;

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
    @Bean("sessionManager")
    public SessionManager sessionManager() {
    	RestAndWebSessionManager webSessionManager = new RestAndWebSessionManager();
        webSessionManager.setSessionIdCookie(sessionIdCookie());
        webSessionManager.setSessionDAO(redisSessionDAO());
		webSessionManager.setGlobalSessionTimeout(3 * 60 * 60 * 1000);
        return webSessionManager;
    }

    @Bean
    public RedisSessionDAO redisSessionDAO() {
    	RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
    	redisSessionDAO.setRedisManager(redisManager());
    	return redisSessionDAO;
    }

    @Bean
	public RedisManager redisManager() {
		RedisManager redisManager = new RedisManager();
		redisManager.setHost(redisManagerProperties().getHost() + ":" + redisManagerProperties().getPort());
		redisManager.setPassword(redisManagerProperties().getPassword());
		redisManager.setDatabase(redisManagerProperties().getDatabase());
		return redisManager;
	}

	@Bean
	public RedisManagerProperties redisManagerProperties() {
		return new RedisManagerProperties();
	}

    @Bean
    public RedisCacheManager redisCacheManager() {
    	RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        redisCacheManager.setPrincipalIdFieldName("id");
        return redisCacheManager;
    }

    /**
     * <p>配置核心安全事务管理器</p>
     * <p>如果有多个relam可以在此处扩展，并通过ModularRealmAuthenticator(securityManager.authenticator)
     * 指定认证策略(默认FirstSuccessfulStrategy)</p>
     * @author yuminjun
     * @date 2020/04/22 11:30:24
     * @param authRealm
     * @return
     */
    @DependsOn("userRealm")
    @Bean(name = "securityManager")
	public SecurityManager securityManager(@Qualifier("userRealm") UserRealm authRealm) {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setCacheManager(redisCacheManager());
		securityManager.setRealm(authRealm);
		securityManager.setSessionManager(sessionManager());
		return securityManager;
	}

	/**
	 * <p>直接使用ShiroFilterFactoryBean，无法自己定义UrlPattern，默认拦截 如需定义拦裁，
	 * 通过FilterRegistrationBean 去注册一个 DelegatingFilterProxy</p>
	 * @author yuminjun
	 * @date 2020/04/22 11:24:44
	 * @param securityManager
	 * @return
	 */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("securityManager") SecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
		shiroFilter.setSecurityManager(securityManager);
		shiroFilter.setUnauthorizedUrl("/sys/unauthorized");
//		shiroFilter.setLoginUrl("/sys/login");

		/* 配置访问权限 */
		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
		filterChainDefinitionMap.put("/sys/login", "anon");
		filterChainDefinitionMap.put("/sys/logout", "anon");
		filterChainDefinitionMap.put("/druid/**", "anon");
		filterChainDefinitionMap.put("/actuator/**", "anon");
		filterChainDefinitionMap.put("/**", "authc");

		Map<String, Filter> filters = new LinkedHashMap<>();
		filters.put("authc", new LocalFormAuthenticationFilter());
		shiroFilter.setFilters(filters);
		shiroFilter.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return shiroFilter;
    }

    /**
     * 开启 Shiro Spring AOP 权限注解的支持
     * @author yuminjun
     * @date 2020/04/22 11:33:40
     * @return
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
