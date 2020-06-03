package com.ycw.cebs.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ycw.cebs.sys.entity.SysUserPerm;
import com.ycw.cebs.sys.mapper.SysUserPermMapper;
import com.ycw.cebs.sys.service.SysUserPermService;

/**
 * 用户权限关联Service接口实现类
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
@Service
public class SysUserPermServiceImpl extends ServiceImpl<SysUserPermMapper, SysUserPerm> implements SysUserPermService{

	@Autowired
	private SysUserPermMapper sysUserPermMapper;

}

