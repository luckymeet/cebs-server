package com.ycw.cebs.sys.vo.param;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class SysUserAddParamVO implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 用户编号
	 */
	private String userNum;

	/**
	 * 真实姓名
	 */
	private String realName;

	/**
	 * 昵称
	 */
	private String nickName;

	/**
	 * 登录名
	 */
	@NotBlank(message = "请输入登录名")
	private String loginName;

	/**
	 * 登录密码
	 */
	@NotBlank(message = "请输入登录密码")
	private String password;

	/**
	 * 头像url
	 */
	private String profilePhotoUrl;

	/**
	 * 性别
	 */
	private Integer sex;

	/**
	 * 生日
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDateTime birthday;

	/**
	 * 手机号
	 */
	private String mobilePhone;

	/**
	 * 电子邮箱
	 */
	@Email(message = "请输入正确的邮箱格式")
	private String eMail;

	/**
	 * 身份证号
	 */
	@Pattern(regexp = "/^[1-9]\\d{5}(18|19|20|(3\\d))\\d{2}((0[1-9])|(1[0-2]))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$/", message = "请输入正确的身份证格式")
	private String idCard;

	/**
	 * QQ
	 */
	private String qq;

	/**
	 * 微信
	 */
	private String wechat;

}
