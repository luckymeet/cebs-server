package com.ycw.cebs.sys.vo;

import java.io.Serializable;

import lombok.Data;

@Data
public class SysPermListVO implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 权限id
	 */
	private Long id;

	/**
	 * 权限名称
	 */
	private String permName;

}
