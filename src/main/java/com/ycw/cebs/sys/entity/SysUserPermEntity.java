package com.ycw.cebs.sys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ycw.common.base.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户权限关联实体类
 * @author yuminjun yuminjun@lexiangbao.com
 * @date 2020/04/22 09:31:06
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
@TableName("sys_user_perm")
public class SysUserPermEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

 	/**
 	 * 用户id
 	 */
	@TableField("user_id")
	private Long userId;

 	/**
 	 * 权限id
 	 */
	@TableField("perm_id")
	private Long permId;

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
