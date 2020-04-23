package com.ycw.cebs.common.utils;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public final class PasswordUtil {

	private PasswordUtil() {}

	/**
	 * 获取密码盐值
	 * @author yuminjun
	 * @date 2020/04/22 16:50:10
	 * @return
	 */
	public static String generateCredentialsSalt() {
		SecureRandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
		return randomNumberGenerator.nextBytes().toHex();
	}

	/**
	 * MD5散列加密
	 * @author yuminjun
	 * @date 2020/04/22 16:50:19
	 * @param plaintextPassword 密码明文
	 * @param credentialsSalt 密码盐值
	 * @return
	 */
	public static String encryptPasswordMD5(String plaintextPassword, String credentialsSalt) {
		return new SimpleHash(Md5Hash.ALGORITHM_NAME, plaintextPassword, ByteSource.Util.bytes(credentialsSalt), 1).toString();
	}

}
