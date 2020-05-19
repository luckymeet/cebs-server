package com.ycw.cebs.sys.vo;

import java.io.Serializable;

import lombok.Data;

@Data
public class LoginUserVO implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 昵称
	 */
	private String nickName;

	/**
	 * 头像url
	 */
	private String profilePhotoUrl;

}
