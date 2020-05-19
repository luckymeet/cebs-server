package com.ycw.cebs.sys.api.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ycw.cebs.common.utils.SessionUtil;
import com.ycw.cebs.common.utils.TreeUtil;
import com.ycw.cebs.common.vo.TreeVO;
import com.ycw.cebs.sys.api.ISysPermApi;
import com.ycw.cebs.sys.entity.SysPermEntity;
import com.ycw.cebs.sys.service.ISysPermService;
import com.ycw.common.response.ResponseVO;

/**
 * 权限Api接口实现类
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
@Service
public class SysPermApiImpl implements ISysPermApi {

	@Autowired
	private ISysPermService sysPermService;

	/**
	 * 查询权限
	 * @author yuminjun
	 * @date 2020/04/23 15:21:27
	 * @param permType 权限类型（为null时查询所有）
	 * @return
	 */
	@Override
	public ResponseVO<List<String>> queryPermList(Integer permType) {
		List<SysPermEntity> permList = this.sysPermService.queryPermListByUserId(SessionUtil.getCurrentUserId());
		List<String> routeList = permList.stream()
				.filter(perm -> null == permType || (permType).equals(perm.getPermType())).map(SysPermEntity::getValue)
				.collect(Collectors.toList());
		return ResponseVO.success(routeList);
	}

	/**
	 * 获取当前用户权限树
	 * @author yuminjun
	 * @date 2020/05/12 17:42:25
	 * @return
	 */
	@Override
	public ResponseVO<List<TreeVO>> queryCurUserPermTree() {
		List<TreeVO> permList = this.sysPermService.queryPermTreeListByUserId(SessionUtil.getCurrentUserId());
		List<TreeVO> treeList = TreeUtil.createTree(permList);
		return ResponseVO.success(treeList);
	}

}
