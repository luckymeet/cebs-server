package com.ycw.cebs.sys.api;

import java.util.List;

import com.ycw.cebs.common.vo.TreeVO;
import com.ycw.common.response.ResponseVO;

/**
 * 权限Api接口
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
public interface SysPermApi {

	/**
	 * 查询权限
	 * @author yuminjun
	 * @date 2020/04/23 15:21:27
	 * @param permType 权限类型（为null时查询所有）
	 * @return
	 */
	ResponseVO<List<String>> queryPermList(Integer permType);

	/**
	 * 获取当前用户权限树
	 * @author yuminjun
	 * @date 2020/05/12 17:42:25
	 * @return
	 */
	ResponseVO<List<TreeVO>> queryCurUserPermTree();

}
