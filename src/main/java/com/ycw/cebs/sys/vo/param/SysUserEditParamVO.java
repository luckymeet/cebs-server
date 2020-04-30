package com.ycw.cebs.sys.vo.param;

import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SysUserEditParamVO extends SysUserAddParamVO {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@NotNull(message = "id不能为空")
	private Long id;

}
