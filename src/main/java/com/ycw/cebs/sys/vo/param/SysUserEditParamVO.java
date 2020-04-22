package com.ycw.cebs.sys.vo.param;

import javax.validation.constraints.Null;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SysUserEditParamVO extends SysUserAddParamVO {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@Null(message = "id不能为空")
	private Long id;

}
