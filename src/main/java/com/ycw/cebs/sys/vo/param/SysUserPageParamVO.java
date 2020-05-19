package com.ycw.cebs.sys.vo.param;

import java.io.Serializable;

import lombok.Data;

@Data
public class SysUserPageParamVO implements Serializable {

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
