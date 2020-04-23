package com.ycw.cebs.sys.api.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.ycw.cebs.common.utils.SessionUtil;
import com.ycw.cebs.sys.api.ISysMenuApi;
import com.ycw.cebs.sys.entity.SysMenuEntity;
import com.ycw.cebs.sys.service.ISysMenuService;
import com.ycw.common.response.ResponseVO;

/**
 * 菜单Api接口实现类
 * @author yuminjun
 * @date 2020/04/23 14:52:19
 * @version 1.00
 *
 * @record
 * <pre>
 * version  author      date          desc
 * -------------------------------------------------
 * 1.00     yuminjun    2020/04/23    新建
 * -------------------------------------------------
 * </pre>
 */
public class SysMenuApiImpl implements ISysMenuApi {

	@Autowired
	private ISysMenuService sysMenuService;

	/**
	 * 查询菜单路由
	 * @author yuminjun
	 * @date 2020/04/23 15:21:27
	 * @param menuType 菜单类型（为null时查询所有）
	 * @return
	 */
	@Override
	public ResponseVO<List<String>> queryRouteList(Integer menuType) {
		List<SysMenuEntity> menuList = sysMenuService.queryMenuListByUserId(SessionUtil.getCurrentUserId());
		List<String> routeList = menuList.stream()
				.filter(menu -> null == menuType || (menuType).equals(menu.getMenuType())).map(SysMenuEntity::getUrl)
				.collect(Collectors.toList());
		return ResponseVO.success(routeList);
	}

}
