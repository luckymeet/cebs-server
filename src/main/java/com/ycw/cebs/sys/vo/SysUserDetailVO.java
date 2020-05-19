package com.ycw.cebs.sys.vo;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ycw.common.constants.CommonConstants;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class SysUserDetailVO implements Serializable {

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

	/**
	 * 昵称
	 */
	private String nickName;

	/**
	 * 登录名
	 */
	private String loginName;

	/**
	 * 登录密码
	 */
	private String password;

	/**
	 * 盐
	 */
	private String salt;

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
	@JsonFormat(pattern = CommonConstants.DATE_FORMAT, timezone = "GMT+8")
	private LocalDateTime birthday;

	/**
	 * 手机号
	 */
	private String mobilePhone;

	/**
	 * 电子邮箱
	 */
	private String eMail;

	/**
	 * 身份证号
	 */
	private String idCard;

	/**
	 * QQ
	 */
	private String qq;

	/**
	 * 微信
	 */
	private String wechat;

	/**
	 * 登录token
	 */
	private String token;

	/**
	 * 状态：0-失效；1-生效
	 */
	private Integer status;

	/**
	 * 登录状态
	 */
	private Integer loginStatus;

	/**
	 * 最后登录时间
	 */
	@JsonFormat(pattern = CommonConstants.DATE_TIME_24HOUR_FORMAT, timezone = "GMT+8")
	private LocalDateTime lastLoginTime;

	/**
	 * 权限Ids,多个以英文逗号隔开
	 */
	private String permIds;

}
