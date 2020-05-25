package com.ycw.cebs.sys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ycw.cebs.common.base.BaseEntity;

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
public class SysUserPerm extends BaseEntity {

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

}
