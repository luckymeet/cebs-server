package com.ycw.cebs.user.vo.param;

import com.ycw.common.base.BaseVO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SysUserPageParamVO extends BaseVO{

	private static final long serialVersionUID = 1L;

	/**
 	 *主键
 	 */
	private Long id;

 	/**
 	 *用户编号
 	 */
	private String userNum;

 	/**
 	 *真实姓名
 	 */
	private String realName;

}
