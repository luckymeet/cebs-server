package com.ycw.cebs.common.constant.sys;

import lombok.Getter;

/**
 * 权限类型
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
public enum PermTypeEnum {

	MENU(1, "菜单"), BUTTON(2, "按钮");

	private Integer code;
	private String desc;

	PermTypeEnum(Integer code, String desc) {
		this.code = code;
		this.desc = desc;
	}
}
