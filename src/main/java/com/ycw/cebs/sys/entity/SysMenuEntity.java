package com.ycw.cebs.sys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ycw.common.base.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 菜单实体类
 * @author yuminjun yuminjun@lexiangbao.com
 * @date 2020/04/22 09:30:50
 * @version v1.00
 *
 * @record
 * <pre>
 * version  author      date          desc
 * -------------------------------------------------
 * 1.00     yuminjun    2020/04/22    新建
 * -------------------------------------------------
 * </pre>
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_menu")
public class SysMenuEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

 	/**
 	 * 上级菜单
 	 */
	@TableField("parent_id")
	private Long parentId;

 	/**
 	 * 排序序号
 	 */
	@TableField("order_num")
	private Integer orderNum;

 	/**
 	 * 菜单名称
 	 */
	@TableField("menu_name")
	private String menuName;

 	/**
 	 * 请求地址
 	 */
	@TableField("url")
	private String url;

 	/**
 	 * 打开方式：1-页签，2-新窗口
 	 */
	@TableField("open_type")
	private Integer openType;

 	/**
 	 * 菜单类型：1-目录，2-菜单，3-按钮
 	 */
	@TableField("menu_type")
	private Integer menuType;

 	/**
 	 * 权限标识
 	 */
	@TableField("perms")
	private String perms;

 	/**
 	 * 图标
 	 */
	@TableField("icon")
	private String icon;

 	/**
 	 * 删除标识：0-未删除，1-删除
 	 */
	@TableField("del_ind")
	private Integer delInd;

 	/**
 	 * 创建人
 	 */
	@TableField("create_user")
	private Long createUser;

 	/**
 	 * 更新人
 	 */
	@TableField("update_user")
	private Long updateUser;

 	/**
 	 * 版本号
 	 */
	@TableField("version")
	private Integer version;

}
