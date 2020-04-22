package com.ycw.cebs.shiro.realm;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ycw.cebs.sys.entity.SysUserEntity;
import com.ycw.cebs.sys.service.ISysUserService;
import com.ycw.common.constants.CommonConstants;

@Component("userRealm")
public class UserRealm extends AuthorizingRealm {

	@Autowired
	private ISysUserService sysUserService;

	public UserRealm() {
		// 默认采用MD5散列加密算法
		// 改用登录失败次数限制的匹配器 modify by yuminjun 2019/08/08
		HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher(Md5Hash.ALGORITHM_NAME);
//		HashedCredentialsMatcher credentialsMatcher = new RetryLimitHashedCredentialsMatcher();
//		credentialsMatcher.setHashAlgorithmName(Md5Hash.ALGORITHM_NAME);

		this.setCredentialsMatcher(credentialsMatcher);
	}

	public UserRealm(CredentialsMatcher credentialsMatcher) {
		// 后续优化登录认证，比如：输入错误几次锁住1小时等，可以扩展HashedCredentialsMatcher去实现
		// ，并指定凭据匹配器，做为构造函数入参，即可。
		this.setCredentialsMatcher(credentialsMatcher);
	}

	/**
	 * 身份认证/登录
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		if (token.getPrincipal() == null) {
			throw new UnknownAccountException();
		}
		String principal = (String) token.getPrincipal();
		SysUserEntity sysUser = null;
		try {
			sysUser = this.sysUserService.getSysUserByPrincipal(principal);
		} catch (Exception ex) {
			throw new AuthenticationException(ex);
		}
		if (sysUser == null) {
			// 用户名错误
			throw new UnknownAccountException();
		}
		if (CommonConstants.INT_NO == sysUser.getStatus()) {
			// 用户禁用
			throw new LockedAccountException();
		}
		SimpleAuthenticationInfo authInfo = new SimpleAuthenticationInfo(sysUser, sysUser.getPassword(), getName());
		authInfo.setCredentialsSalt(ByteSource.Util.bytes(sysUser.getSalt()));
		return authInfo;
	}

	/**
	 * 授权
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		// 根据当前 realm认证通过身份，获取授权（防止多realm交叉问题））
		Collection<SysUserEntity> userPrincipals = principals.fromRealm(getName());
		if (!CollectionUtils.isEmpty(userPrincipals)) {
			SysUserEntity user = userPrincipals.iterator().next();
			List<SysMenuEntity> menuList = this.userRealmService.queryMenusByUserId(user.getId());
            if (!CollectionUtils.isEmpty(menuList)) {
                // 遍历权限，进行授权
                Set<String> permissions = new LinkedHashSet<String>();
                menuList.stream().filter(menu -> StringUtils.isNotBlank(menu.getPerms()))
                        .forEach(menu -> permissions.add(menu.getPerms()));
                authorizationInfo.setStringPermissions(permissions);
            }
		}

		return authorizationInfo;
	}

	/**
	 * 清空指定用户授权缓存
	 */
	public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
		super.clearCachedAuthorizationInfo(principals);
	}

	/**
	 * 清空所有用户授权缓存
	 */
	public void clearAllCachedAuthorizationInfo() {
		getAuthorizationCache().clear();
	}

}
