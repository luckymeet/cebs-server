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
import com.ycw.cebs.common.vo.TreeVO;
import com.ycw.cebs.sys.entity.SysPerm;
import com.ycw.cebs.sys.entity.SysUserPerm;
import com.ycw.cebs.sys.mapper.SysPermMapper;
import com.ycw.cebs.sys.mapper.SysUserPermMapper;
import com.ycw.cebs.sys.service.SysPermService;
import com.ycw.common.base.BaseServiceImpl;
import com.ycw.common.exception.SysException;

/**
 * 系统权限Service接口实现类
 *
 * @author yuminjun yuminjun@lexiangbao.com
 * @date 2020/04/22 09:30:50
 * @version v1.00
 *
 * @record
 *
 *         <pre>
 * version  author      date          desc
 * -------------------------------------------------
 * 1.00     yuminjun    2020/04/22    新建
 * -------------------------------------------------
 *         </pre>
 */
@Service
public class SysPermServiceImpl extends BaseServiceImpl<SysPermMapper, SysPerm> implements SysPermService {

	@Autowired
	private SysPermMapper sysPermMapper;

	@Autowired
	private SysUserPermMapper sysUserPermMapper;

	/**
	 * 根据用户id查询权限
	 *
	 * @author yuminjun
	 * @date 2020/04/22 10:57:35
	 * @param userId 用户id
	 * @return
	 * @throws SysException
	 */
	@Override
	public List<SysPerm> queryPermListByUserId(Long userId) {
		// 根据用户id查询用户权限关联数据列表
		List<SysUserPerm> userPermList = queryUserPermByUserId(userId);
		if (CollectionUtils.isEmpty(userPermList)) {
			return Collections.emptyList();
		}
		List<Long> permIdList = userPermList.stream().map(SysUserPerm::getPermId).collect(Collectors.toList());
		// 根据权限id集合查询权限
		return queryPermListByPermIdList(permIdList);
	}

	/**
	 * 根据权限id集合查询权限
	 *
	 * @author yuminjun
	 * @date 2020/04/22 10:56:26
	 * @param permIds 权限id集合
	 * @return
	 */
	@Override
	public List<SysPerm> queryPermListByPermIdList(Collection<Long> permIds) {
		if (CollectionUtils.isEmpty(permIds)) {
			return Collections.emptyList();
		}
		LambdaQueryWrapper<SysPerm> queryWrapper = Wrappers.lambdaQuery();
		queryWrapper.in(SysPerm::getId, permIds);
		return sysPermMapper.selectList(queryWrapper);
	}

	/**
	 * 根据用户id查询用户权限关联数据列表
	 *
	 * @author yuminjun
	 * @date 2020/04/22 10:57:08
	 * @param userId 用户id
	 * @return
	 */
	@Override
	public List<SysUserPerm> queryUserPermByUserId(Long userId) {
		LambdaQueryWrapper<SysUserPerm> queryWrapper = Wrappers.lambdaQuery();
		queryWrapper.eq(SysUserPerm::getUserId, userId);
		return sysUserPermMapper.selectList(queryWrapper);
	}

	/**
	 * 查询用户权限列表
	 *
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
