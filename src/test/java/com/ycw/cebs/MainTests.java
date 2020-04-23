package com.ycw.cebs;

import com.ycw.cebs.common.utils.PasswordUtil;

class MainTests {

	public static void main(String[] args) {
		String salt = PasswordUtil.generateCredentialsSalt();
		System.out.println(salt);
		System.out.println(PasswordUtil.encryptPasswordMD5("123456", salt));
	}

}
