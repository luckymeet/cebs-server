package com.ycw.cebs.common.constant.sys;

import lombok.Getter;

/**
 * 客户端类型
 *
 * @author yuminjun
 * @date 2020/04/22 11:49:26
 * @version 1.00
 *
 * @record
 * <pre>
 * version  author      date          desc
 * -------------------------------------------------
 * 1.00     yuminjun    2020/04/22    新建
 * -------------------------------------------------
 * </pre>
 */
@Getter
public enum ClientTypeEnum {

	BACK_END(1, "后台"), PORTAL(2, "门户");

	private Integer code;
	private String desc;

	ClientTypeEnum(Integer code, String desc) {
		this.code = code;
		this.desc = desc;
	}
}
