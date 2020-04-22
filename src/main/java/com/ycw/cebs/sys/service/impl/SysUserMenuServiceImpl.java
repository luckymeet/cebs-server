package com.ycw.cebs.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ycw.cebs.sys.entity.SysUserMenuEntity;
import com.ycw.cebs.sys.mapper.ISysUserMenuMapper;
import com.ycw.cebs.sys.service.ISysUserMenuService;

/**
 * 用户菜单关联Service接口实现类
 * @author yuminjun yuminjun@lexiangbao.com
 * @date 2020/04/22 09:31:06
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
@Service("sysUserMenuService")
public class SysUserMenuServiceImpl extends ServiceImpl<ISysUserMenuMapper, SysUserMenuEntity> implements ISysUserMenuService{

	@Autowired
	private ISysUserMenuMapper sysUserMenuMapper;

}

