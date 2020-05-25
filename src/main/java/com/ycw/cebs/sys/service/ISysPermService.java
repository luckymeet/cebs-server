package com.ycw.cebs.sys.service;

import java.util.Collection;
import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ycw.cebs.common.vo.TreeVO;
import com.ycw.cebs.sys.entity.SysPerm;
import com.ycw.cebs.sys.entity.SysUserPerm;

/**
 * 系统权限Service接口
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
public interface ISysPermService extends IService<SysPerm>{

	/**
	 * 根据用户id查询权限
	 * @author yuminjun
	 * @date 2020/04/22 10:57:35
	 * @param userId 用户id
	 * @return
	 */
	List<SysPerm> queryPermListByUserId(Long userId);

	/**
	 * 根据权限id集合查询权限
	 * @author yuminjun
	 * @date 2020/04/22 10:56:26
	 * @param permIds 权限id集合
	 * @return
	 */
	List<SysPerm> queryPermListByPermIdList(Collection<Long> permIds);

	/**
	 * 根据用户id查询用户权限关联数据列表
	 * @author yuminjun
	 * @date 2020/04/22 10:57:08
	 * @param userId 用户id
	 * @return
	 */
	List<SysUserPerm> queryUserPermByUserId(Long userId);

	/**
	 * 查询用户权限列表
	 * @author yuminjun
	 * @date 2020/05/13 09:53:19
	 * @param userId 用户id
	 * @return
	 */
	List<TreeVO> queryPermTreeListByUserId(Long userId);

}
