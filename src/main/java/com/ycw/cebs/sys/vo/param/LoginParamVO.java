package com.ycw.cebs.sys.vo.param;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.ycw.common.base.BaseVO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class LoginParamVO extends BaseVO {

	private static final long serialVersionUID = 1L;

	/**
	 * 用户名
	 */
	@NotEmpty(message = "用户名不能为空")
	@Length(min = 6, max = 20, message = "用户名为6-20位字符")
	private String username;

	/**
	 * 密码
	 */
	@NotEmpty(message = "密码不能为空")
	@Length(min = 6, max = 20, message = "密码为6-20位字符")
	private String password;

}
