package com.ycw.cebs.common.utils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

import com.ycw.cebs.shiro.realm.UserRealm;
import com.ycw.cebs.sys.entity.SysUserEntity;

public final class SessionUtil {
	private SessionUtil() {
    }

    /**
     * <p>清空shiro(当前登录)用户证认/授权缓存</p>
     * <p>目前只缓存授权，认证因只登录一次且未实现会话持久话（分布式会话）故没有缓存认证</p>
     * <p>可用于用户登录前，或注销后 清空授权缓存信息</p>
     */
	public static void clearCurUserCachedAuthInfo() {
		PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
		RealmSecurityManager securityManager = (RealmSecurityManager) SecurityUtils.getSecurityManager();
		securityManager.getRealms().forEach(realm -> {
			if (realm instanceof UserRealm) {
				((UserRealm) realm).clearCachedAuthorizationInfo(principals);
			}
		});
	}

    /**
     * <p>清空shiro(所有)用户证认/授权缓存</p>
     * <p>目前只缓存授权，认证因只登录一次且未实现会话持久话（分布式会话）故没有缓存认证</p>
     * <p>用于修改角色权限时调用，清除所有用户权限</p>
     */
    public static void clearAllCachedAuthInfo() {
        RealmSecurityManager securityManager = (RealmSecurityManager) SecurityUtils.getSecurityManager();
        securityManager.getRealms().forEach(realm -> {
            if (realm instanceof UserRealm) {
                ((UserRealm) realm).clearAllCachedAuthorizationInfo();
            }
        });
    }

    /**
     * 获取当前登录用户
     */
	public static SysUserEntity getCurrentUser() {
		Subject subject = SecurityUtils.getSubject();
		if (subject == null || subject.getPrincipal() == null) {
			return null;
		}
		return (SysUserEntity) subject.getPrincipal();
	}

    /**
     * 获取当前登录用户ID
     */
	public static Long getCurrentUserId() {
		SysUserEntity user = getCurrentUser();
		return user == null ? null : user.getId();
	}

    public static Session getCurrentSession() {
        return SecurityUtils.getSubject().getSession(true);
    }

}
