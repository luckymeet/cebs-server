package com.ycw.cebs.sys.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ycw.cebs.common.base.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 系统用户实体类
 * @author yuminjun
 * @date 2020/04/21 15:53:53
 * @version 1.00
 *
 * @record
 * <pre>
 * version  author      date          desc
 * -------------------------------------------------
 * 1.00     yuminjun    2020/04/21    新建
 * -------------------------------------------------
 * </pre>
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_user")
public class SysUser extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 用户编号
	 */
	@TableField("user_num")
	private String userNum;

	/**
	 * 真实姓名
	 */
	@TableField("real_name")
	private String realName;

	/**
	 * 昵称
	 */
	@TableField("nick_name")
	private String nickName;

	/**
	 * 登录名
	 */
	@TableField("login_name")
	private String loginName;

	/**
	 * 登录密码
	 */
	@TableField("password")
	private String password;

	/**
	 * 盐
	 */
	@TableField("salt")
	private String salt;

	/**
	 * 头像url
	 */
	@TableField("profile_photo_url")
	private String profilePhotoUrl;

	/**
	 * 性别
	 */
	@TableField("sex")
	private Integer sex;

	/**
	 * 生日
	 */
	@TableField("birthday")
	private LocalDate birthday;

	/**
	 * 手机号
	 */
	@TableField("mobile_phone")
	private String mobilePhone;

	/**
	 * 电子邮箱
	 */
	@TableField("e_mail")
	private String eMail;

	/**
	 * 身份证号
	 */
	@TableField("id_card")
	private String idCard;

	/**
	 * QQ
	 */
	@TableField("qq")
	private String qq;

	/**
	 * 微信
	 */
	@TableField("wechat")
	private String wechat;

	/**
	 * 登录token
	 */
	@TableField("token")
	private String token;

	/**
	 * 状态：0-失效；1-生效
	 */
	@TableField("status")
	private Integer status;

	/**
	 * 登录状态
	 */
	@TableField("login_status")
	private Integer loginStatus;

	/**
	 * 最后登录时间
	 */
	@TableField("last_login_time")
	private LocalDateTime lastLoginTime;

	/**
	 * 客户端id：1-后台，2-门户
	 */
	@TableField("client_id")
	private Integer clientId;

	/**
	 * 创建链，后台用户有值
	 */
	@TableField("create_chain")
	private String createChain;

}
