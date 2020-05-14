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
import com.ycw.cebs.common.vo.TreeVO;
import com.ycw.cebs.sys.entity.SysPermEntity;
import com.ycw.cebs.sys.entity.SysUserPermEntity;
import com.ycw.cebs.sys.mapper.ISysPermMapper;
import com.ycw.cebs.sys.mapper.ISysUserPermMapper;
import com.ycw.cebs.sys.service.ISysPermService;
import com.ycw.common.constants.CommonConstants;
import com.ycw.common.exception.SysException;

/**
 * 系统权限Service接口实现类
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
@Service("sysPermService")
public class SysPermServiceImpl extends ServiceImpl<ISysPermMapper, SysPermEntity> implements ISysPermService{

	@Autowired
	private ISysPermMapper sysPermMapper;

	@Autowired
	private ISysUserPermMapper sysUserPermMapper;

	/**
	 * 根据用户id查询权限
	 * @author yuminjun
	 * @date 2020/04/22 10:57:35
	 * @param userId 用户id
	 * @return
	 * @throws SysException
	 */
	@Override
	public List<SysPermEntity> queryPermListByUserId(Long userId) {
		// 根据用户id查询用户权限关联数据列表
		List<SysUserPermEntity> userPermList = queryUserPermByUserId(userId);
		if (CollectionUtils.isEmpty(userPermList)) {
			return Collections.emptyList();
		}
		List<Long> permIdList = userPermList.stream().map(SysUserPermEntity::getPermId).collect(Collectors.toList());
		// 根据权限id集合查询权限
		List<SysPermEntity> permList = queryPermListByPermIdList(permIdList);
		return permList;
	}

	/**
	 * 根据权限id集合查询权限
	 * @author yuminjun
	 * @date 2020/04/22 10:56:26
	 * @param permIds 权限id集合
	 * @return
	 */
	@Override
	public List<SysPermEntity> queryPermListByPermIdList(Collection<Long> permIds) {
		if (CollectionUtils.isEmpty(permIds)) {
			return Collections.emptyList();
		}
		LambdaQueryWrapper<SysPermEntity> queryWrapper = Wrappers.lambdaQuery();
		queryWrapper.in(SysPermEntity::getId, permIds);
		queryWrapper.eq(SysPermEntity::getDelInd, CommonConstants.INT_NO);
		List<SysPermEntity> permList = sysPermMapper.selectList(queryWrapper);
		return permList;
	}

	/**
	 * 根据用户id查询用户权限关联数据列表
	 * @author yuminjun
	 * @date 2020/04/22 10:57:08
	 * @param userId 用户id
	 * @return
	 */
	@Override
	public List<SysUserPermEntity> queryUserPermByUserId(Long userId) {
		LambdaQueryWrapper<SysUserPermEntity> queryWrapper = Wrappers.lambdaQuery();
		queryWrapper.eq(SysUserPermEntity::getUserId, userId);
		queryWrapper.eq(SysUserPermEntity::getDelInd, CommonConstants.INT_NO);
		List<SysUserPermEntity> userPermlist = sysUserPermMapper.selectList(queryWrapper);
		return userPermlist;
	}

	/**
	 * 查询用户权限列表
	 * @author yuminjun
	 * @date 2020/05/13 09:53:19
	 * @param userId 用户id
	 * @return
	 */
	@Override
	public List<TreeVO> queryPermTreeListByUserId(Long userId) {
		 return sysPermMapper.queryPermTreeListByUserId(userId);
	}

}

