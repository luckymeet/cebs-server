package com.ycw.cebs.sys.api.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ycw.cebs.common.utils.SessionUtil;
import com.ycw.cebs.common.utils.TreeUtil;
import com.ycw.cebs.common.vo.TreeVO;
import com.ycw.cebs.sys.api.ISysPermApi;
import com.ycw.cebs.sys.entity.SysPermEntity;
import com.ycw.cebs.sys.entity.SysUserPermEntity;
import com.ycw.cebs.sys.service.ISysPermService;
import com.ycw.cebs.sys.service.ISysUserPermService;
import com.ycw.cebs.sys.vo.SysPermListVO;
import com.ycw.common.constants.CommonConstants;
import com.ycw.common.response.ResponseVO;
import com.ycw.common.utils.BeanHandleUtils;

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
@Service("sysPermApi")
public class SysPermApiImpl implements ISysPermApi {

	@Autowired
	private ISysPermService sysPermService;

	@Autowired
	private ISysUserPermService sysUserPermService;

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
	 * 查询当前用户一级权限列表
	 * @author yuminjun
	 * @date 2020/04/30 14:43:30
	 * @return
	 */
	@Override
	public ResponseVO<List<SysPermListVO>> queryCurUserOneLevelPermList() {
		// 查询当前用户的用户权限关联数据
		List<SysUserPermEntity> userPermList = sysPermService.queryUserPermByUserId(SessionUtil.getCurrentUserId());
		// 查询一级权限列表
		List<SysPermEntity> permList = queryOneLevelPermListByPermIdList(userPermList);
		List<SysPermListVO> listVo = BeanHandleUtils.listCopy(permList, SysPermListVO.class);
		return ResponseVO.success(listVo);
	}

	/**
	 * 根据用户权限关联数据查询一级权限
	 * @author yuminjun
	 * @date 2020/04/30 16:35:22
	 * @param userPermList 用户权限关联数据列表
	 * @return
	 */
	private List<SysPermEntity> queryOneLevelPermListByPermIdList(List<SysUserPermEntity> userPermList) {
		List<Long> permIdList = userPermList.stream().map(SysUserPermEntity::getPermId).collect(Collectors.toList());
		if (CollectionUtils.isEmpty(permIdList)) {
			return Collections.emptyList();
		}
		LambdaQueryWrapper<SysPermEntity> queryWrapper = Wrappers.lambdaQuery();
		queryWrapper.in(SysPermEntity::getId, permIdList);
		queryWrapper.eq(SysPermEntity::getParentId, 0L);
		queryWrapper.eq(SysPermEntity::getDelInd, CommonConstants.INT_NO);
		List<SysPermEntity> permList = this.sysPermService.list(queryWrapper);
		return permList;
	}

	/**
	 * 根据上级权限id查询权限列表
	 * @author yuminjun
	 * @date 2020/04/30 16:42:56
	 * @param parentId
	 * @return
	 */
	@Override
	public ResponseVO<List<SysPermListVO>> queryPermListByParentId(Long parentId) {
		LambdaQueryWrapper<SysPermEntity> queryWrapper = Wrappers.lambdaQuery();
		queryWrapper.eq(SysPermEntity::getParentId, parentId);
		queryWrapper.eq(SysPermEntity::getDelInd, CommonConstants.INT_NO);
		List<SysPermEntity> permList = this.sysPermService.list(queryWrapper);
		List<SysPermListVO> listVo = BeanHandleUtils.listCopy(permList, SysPermListVO.class);
		return ResponseVO.success(listVo);
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
