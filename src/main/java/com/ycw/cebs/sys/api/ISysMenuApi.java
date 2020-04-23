package com.ycw.cebs.sys.api;

import java.util.List;

import com.ycw.common.response.ResponseVO;

/**
 * 菜单Api接口
 * @author yuminjun
 * @date 2020/04/23 14:51:23
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
public interface ISysMenuApi {

	/**
	 * 查询菜单路由
	 * @author yuminjun
	 * @date 2020/04/23 15:21:27
	 * @param menuType 菜单类型（为null时查询所有）
	 * @return
	 */
	ResponseVO<List<String>> queryRouteList(Integer menuType);

}
