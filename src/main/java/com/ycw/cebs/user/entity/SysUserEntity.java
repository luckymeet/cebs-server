package com.ycw.cebs.user.entity;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ycw.common.base.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @类名称 SysUser
 * @类描述 <pre>【系统用户表】实体类</pre>
 * @作者 yuminjun
 * @创建时间 2019年12月27日
 * @版本 v1.00
 * @修改记录 <pre>
 * 版本     		修改人 	修改时间    	 	修改内容	描述
 * ----------------------------------------------
 * 1.00 	yuminjun     	2019年12月27日 	新建
 * ----------------------------------------------
 * </pre>
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_user")
public class SysUserEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
 	 *用户编号
 	 */
	@TableField("user_num")
	private String userNum;

 	/**
 	 *真实姓名
 	 */
	@TableField("real_name")
	private String realName;

 	/**
 	 *昵称
 	 */
	@TableField("nick_name")
	private String nickName;

 	/**
 	 *登录名
 	 */
	@TableField("login_name")
	private String loginName;

 	/**
 	 *登录密码
 	 */
	@TableField("password")
	private String password;

 	/**
 	 *盐
 	 */
	@TableField("salt")
	private String salt;

 	/**
 	 *头像url
 	 */
	@TableField("profile_photo_url")
	private String profilePhotoUrl;

 	/**
 	 *性别
 	 */
	@TableField("sex")
	private Integer sex;

 	/**
 	 *生日
 	 */
	@TableField("birthday")
	private LocalDateTime birthday;

 	/**
 	 *手机号
 	 */
	@TableField("mobile_phone")
	private String mobilePhone;

 	/**
 	 *电子邮箱
 	 */
	@TableField("e_mail")
	private String eMail;

 	/**
 	 *身份证号
 	 */
	@TableField("id_card")
	private String idCard;

 	/**
 	 *QQ
 	 */
	@TableField("qq")
	private String qq;

 	/**
 	 *微信
 	 */
	@TableField("wechat")
	private String wechat;

 	/**
 	 *登录token
 	 */
	@TableField("token")
	private String token;

 	/**
 	 *状态：0-失效；1-生效
 	 */
	@TableField("status")
	private Integer status;

 	/**
 	 *登录状态
 	 */
	@TableField("login_status")
	private Integer loginStatus;

 	/**
 	 *最后登录时间
 	 */
	@TableField("last_login_time")
	private LocalDateTime lastLoginTime;

}
