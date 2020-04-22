package com.ycw.cebs.shiro.match;

import java.util.concurrent.TimeUnit;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import com.ycw.common.utils.SpringUtils;

/**
 * @类名称 RetryLimitHashedCredentialsMatcher.java
 * @类描述
 *
 *      <pre>
 * 含有失败次数限制的登录认证匹配器, 通过redis实现，有过期时间设置
 *      </pre>
 *
 * @作者 yuminjun yuminjun@lexiangbao.com
 * @创建时间 2019年8月8日 下午12:04:42
 * @版本 1.00
 *
 * @修改记录
 *
 *       <pre>
 *     版本                       修改人 		修改日期 		 修改内容描述
 *     ----------------------------------------------
 *     1.00 	yuminjun 	2019年8月8日
 *     ----------------------------------------------
 *       </pre>
 */
public class RetryLimitHashedCredentialsMatcher extends HashedCredentialsMatcher {

	private static Logger logger = LoggerFactory.getLogger(RetryLimitHashedCredentialsMatcher.class);

	private int maxRetryCount = 5;// 默认最大登录失败次数

	private long expireTime = 0L;// 过期时间(单位，秒)，小于等于0时则缓存不过期

	@Override
	public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
		// 由于shiro的初始化通常早于其他类的初始化，因此不采用注入的方式获取bean
//		RedisTemplate<String, Integer> redisTemplate = (RedisTemplate<String, Integer>) SpringUtils.getBean("redisTemplate");// reids操作对象
//		UserRealmService userRealmService = SpringUtils.getBean(UserRealmService.class);
//		SysUserService sysUserService = SpringUtils.getBean(SysUserService.class);
//		SysDictCommonService sysDictCommonService = SpringUtils.getBean(SysDictCommonService.class);
//	    Locks locks = SpringUtils.getBean(Locks.class);
//
//		// 从数据字典中获取最大登录失败次数
//		SysDictCommonVo sysDictCommonVo = sysDictCommonService.queryByDictTypeAndDictLabel("login_retry_limit", "retry_times");
//		if(sysDictCommonVo != null && StringUtil.isNotBlank(sysDictCommonVo.getDictValue())) {
//			maxRetryCount = Integer.parseInt(sysDictCommonVo.getDictValue());
//		}
//
//		String username = (String) token.getPrincipal();// 获取用户名
//		String retryKey = "RetryCount_" + username;// reids中的key, 值为当前用户登录失败次数
		boolean matches = false;
//
//		DistributedLock lock = locks.getDistributedLock("Lock_" + retryKey);
//		lock.lock();
//		try {
//			Integer retryCount = redisTemplate.opsForValue().get(retryKey);
//			// 如果用户登陆失败次数大于5次, 抛出锁定用户异常, 并修改数据库用户状态为 禁用
//			if (retryCount != null && retryCount + 1 > maxRetryCount) {
//				SysUser user = null;
//				try {
//					user = userRealmService.findUserByLoginNameOrMobile(username);
//				} catch (Exception ex) {
//					throw new AuthenticationException(ex);
//				}
//				sysUserService.changeUserStatus(user.getId(), 0);
//				redisTemplate.delete(retryKey);// 修改数据库状态后清除redis中的key
//				throw new LockedAccountException();// 抛出用户锁定异常
//			}
//
//			// 用户账户名密码匹配
			matches = super.doCredentialsMatch(token, info);
//
//			if (matches) {// 如果账户名密码匹配成功，则从缓存中将该用户登录计数 清除
//				redisTemplate.delete(retryKey);
//			} else {// 如果不成功，缓存中登录失败次数加1
//				redisTemplate.opsForValue().increment(retryKey, 1);
//				if(expireTime > 0) {
//					redisTemplate.expire(retryKey, expireTime, TimeUnit.SECONDS);// 设置缓存过期时间
//				}
//			}
//		} finally {
//			lock.unlock();
//		}
//
		return matches;
	}

}
