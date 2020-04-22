package com.ycw.cebs.sys.service.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ycw.cebs.sys.entity.SysMenuEntity;
import com.ycw.cebs.sys.entity.SysUserMenuEntity;
import com.ycw.cebs.sys.mapper.ISysMenuMapper;
import com.ycw.cebs.sys.mapper.ISysUserMenuMapper;
import com.ycw.cebs.sys.service.ISysMenuService;
import com.ycw.common.constants.CommonConstants;
import com.ycw.common.exception.SysException;

/**
 * 菜单Service接口实现类
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
@Service("sysMenuService")
public class SysMenuServiceImpl extends ServiceImpl<ISysMenuMapper, SysMenuEntity> implements ISysMenuService{

	@Autowired
	private ISysMenuMapper sysMenuMapper;

	@Autowired
	private ISysUserMenuMapper sysUserMenuMapper;

	/**
	 * 根据用户id查询菜单
	 * @author yuminjun
	 * @date 2020/04/22 10:57:35
	 * @param userId 用户id
	 * @return
	 * @throws SysException
	 */
	@Override
	public List<SysMenuEntity> queryMenuListByUserId(Long userId) {
		// 根据用户id查询用户菜单关联数据列表
		List<SysUserMenuEntity> userMenuList = queryUserMenuByUserId(userId);
		if (CollectionUtils.isEmpty(userMenuList)) {
			return Collections.emptyList();
		}
		List<Long> menuIdList = userMenuList.stream().map(SysUserMenuEntity::getMenuId).collect(Collectors.toList());
		// 根据菜单id集合查询菜单
		List<SysMenuEntity> menuList = queryMenuListByMenuIdList(menuIdList);
		return menuList;
	}

	/**
	 * 根据菜单id集合查询菜单
	 * @author yuminjun
	 * @date 2020/04/22 10:56:26
	 * @param menuIds 菜单id集合
	 * @return
	 */
	@Override
	public List<SysMenuEntity> queryMenuListByMenuIdList(Collection<Long> menuIds) {
		LambdaQueryWrapper<SysMenuEntity> queryWrapper = Wrappers.lambdaQuery();
		queryWrapper.in(SysMenuEntity::getId, menuIds);
		queryWrapper.eq(SysMenuEntity::getDelInd, CommonConstants.INT_NO);
		List<SysMenuEntity> menuList = sysMenuMapper.selectList(queryWrapper);
		return menuList;
	}

	/**
	 * 根据用户id查询用户菜单关联数据列表
	 * @author yuminjun
	 * @date 2020/04/22 10:57:08
	 * @param userId 用户id
	 * @return
	 */
	private List<SysUserMenuEntity> queryUserMenuByUserId(Long userId) {
		LambdaQueryWrapper<SysUserMenuEntity> queryWrapper = Wrappers.lambdaQuery();
		queryWrapper.eq(SysUserMenuEntity::getUserId, userId);
		queryWrapper.eq(SysUserMenuEntity::getDelInd, CommonConstants.INT_NO);
		List<SysUserMenuEntity> userMenulist = sysUserMenuMapper.selectList(queryWrapper);
		return userMenulist;
	}

}

