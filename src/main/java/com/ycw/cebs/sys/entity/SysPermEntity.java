package com.ycw.cebs.sys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ycw.cebs.common.base.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 系统权限实体类
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
@TableName("sys_perm")
public class SysPermEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

 	/**
 	 * 上级权限
 	 */
	@TableField("parent_id")
	private Long parentId;

 	/**
 	 * 排序序号
 	 */
	@TableField("order_num")
	private Integer orderNum;

 	/**
 	 * 权限名称
 	 */
	@TableField("perm_name")
	private String permName;

 	/**
 	 * 权限类型：1-菜单，2-按钮
 	 */
	@TableField("perm_type")
	private Integer permType;

 	/**
 	 * 权限标识
 	 */
	@TableField("value")
	private String value;

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
