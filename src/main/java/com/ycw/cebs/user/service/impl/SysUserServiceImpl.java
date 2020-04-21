package com.ycw.cebs.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ycw.cebs.user.entity.SysUserEntity;
import com.ycw.cebs.user.mapper.ISysUserMapper;
import com.ycw.cebs.user.service.ISysUserService;
import com.ycw.cebs.user.vo.SysUserListVO;
import com.ycw.cebs.user.vo.param.SysUserListParamVO;
import com.ycw.common.page.PageParams;

/**
 * 系统用户Service接口实现类
 * @author yuminjun
 * @date 2020/04/21 15:50:44
 * @version 1.00
 *
 * @record
 * <pre>
 * version  author      date          desc
 * -------------------------------------------------
 * 1.00     yuminjun    2020/04/21    新建
 * -------------------------------------------------
 * </pre>
 */
@Service("sysUserService")
public class SysUserServiceImpl extends ServiceImpl<ISysUserMapper, SysUserEntity> implements ISysUserService {

	@Autowired
	private ISysUserMapper sysUserMapper;

	/**
	 * 用户列表分页查询
	 * @author yuminjun
	 * @date 2020/04/21 14:52:30
	 * @param vo 查询参数
	 * @param pageParams 分页参数（为空时默认查询所有）
	 * @return
	 */
	@Override
	public List<SysUserListVO> querySysUserList(SysUserListParamVO vo, PageParams pageParams) {
		return sysUserMapper.querySysUserList(vo);
	}

}
