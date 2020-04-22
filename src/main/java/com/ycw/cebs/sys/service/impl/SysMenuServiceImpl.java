package com.ycw.cebs.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ycw.cebs.sys.entity.SysMenuEntity;
import com.ycw.cebs.sys.mapper.ISysMenuMapper;
import com.ycw.cebs.sys.service.ISysMenuService;

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

}

