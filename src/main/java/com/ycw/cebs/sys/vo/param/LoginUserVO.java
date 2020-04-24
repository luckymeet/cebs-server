package com.ycw.cebs.sys.vo.param;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
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
