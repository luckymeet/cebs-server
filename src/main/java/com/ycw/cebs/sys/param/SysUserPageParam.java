package com.ycw.cebs.sys.param;

import java.io.Serializable;

import lombok.Data;

@Data
public class SysUserPageParam implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	private Long id;

	/**
	 * 用户编号
	 */
	private String userNum;

	/**
	 * 真实姓名
	 */
	private String realName;

}
