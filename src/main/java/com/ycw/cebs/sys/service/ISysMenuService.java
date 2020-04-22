package com.ycw.cebs.sys.service;

import java.util.Collection;
import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ycw.cebs.sys.entity.SysMenuEntity;
import com.ycw.common.exception.SysException;

/**
 * 菜单Service接口
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
public interface ISysMenuService extends IService<SysMenuEntity>{

	/**
	 * 根据用户id查询菜单
	 * @author yuminjun
	 * @date 2020/04/22 10:57:35
	 * @param userId 用户id
	 * @return
	 */
	List<SysMenuEntity> queryMenuListByUserId(Long userId);

	/**
	 * 根据菜单id集合查询菜单
	 * @author yuminjun
	 * @date 2020/04/22 10:56:26
	 * @param menuIds 菜单id集合
	 * @return
	 */
	List<SysMenuEntity> queryMenuListByMenuIdList(Collection<Long> menuIdList);

}
