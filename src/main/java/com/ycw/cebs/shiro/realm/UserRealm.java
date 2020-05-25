package com.ycw.cebs.shiro.realm;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
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

import com.ycw.cebs.sys.entity.SysPerm;
import com.ycw.cebs.sys.entity.SysUser;
import com.ycw.cebs.sys.service.ISysPermService;
import com.ycw.cebs.sys.service.ISysUserService;
import com.ycw.common.constants.CommonConstants;

@Component("userRealm")
public class UserRealm extends AuthorizingRealm {

	@Autowired
	private ISysUserService sysUserService;

	@Autowired
	private ISysPermService sysPermService;

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
	 * 身份认证
	 * @author yuminjun
	 * @date 2020/04/22 11:18:39
	 * @param token
	 * @return
	 * @throws AuthenticationException
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		if (token.getPrincipal() == null) {
			throw new UnknownAccountException();
		}
		String principal = (String) token.getPrincipal();
		SysUser sysUser = null;
		try {
			sysUser = this.sysUserService.getUserByLoginAccount(principal);
		} catch (Exception e) {
			throw new AuthenticationException(e);
		}
		if (sysUser == null) {
			throw new UnknownAccountException();
		}
		if (CommonConstants.INT_NO.equals(sysUser.getStatus())) {
			throw new DisabledAccountException();
		}
		SimpleAuthenticationInfo authInfo = new SimpleAuthenticationInfo(sysUser, sysUser.getPassword(), getName());
		authInfo.setCredentialsSalt(ByteSource.Util.bytes(sysUser.getSalt()));
		return authInfo;
	}

	/**
	 * 授权
	 * @author yuminjun
	 * @date 2020/04/22 11:17:28
	 * @param principals
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		// 根据当前 realm认证通过身份，获取授权（防止多realm交叉问题））
		Collection<SysUser> userPrincipals = principals.fromRealm(getName());
		if (!CollectionUtils.isEmpty(userPrincipals)) {
			SysUser user = userPrincipals.iterator().next();
			List<SysPerm> permList = this.sysPermService.queryPermListByUserId(user.getId());
			if (!CollectionUtils.isEmpty(permList)) {
				// 遍历权限，进行授权
				Set<String> permissions = permList.stream().map(SysPerm::getValue).collect(Collectors.toSet());
				authorizationInfo.setStringPermissions(permissions);
			}
		}
		return authorizationInfo;
	}

	/**
	 * 清空指定用户授权缓存
	 * @author yuminjun
	 * @date 2020/04/23 14:39:08
	 * @param principals
	 */
	@Override
	public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
		super.clearCachedAuthorizationInfo(principals);
	}

	/**
	 * 清空所有用户授权缓存
	 * @author yuminjun
	 * @date 2020/04/23 14:40:11
	 */
	public void clearAllCachedAuthorizationInfo() {
		getAuthorizationCache().clear();
	}

}
