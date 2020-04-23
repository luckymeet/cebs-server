package com.ycw.cebs.common.constants.sys;

import lombok.Getter;

/**
 * 菜单类型
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
public enum MenuTypeEnum {

	CATALOGUE(1, "目录"), MENU(2, "菜单"), BUTTON(3, "按钮");

	private Integer code;
	private String desc;

	MenuTypeEnum(Integer code, String desc) {
		this.code = code;
		this.desc = desc;
	}
}
