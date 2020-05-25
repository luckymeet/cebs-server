package com.ycw.cebs.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ycw.cebs.common.constant.sys.ClientTypeEnum;
import com.ycw.cebs.sys.entity.SysUser;
import com.ycw.cebs.sys.mapper.ISysUserMapper;
import com.ycw.cebs.sys.param.SysUserListParam;
import com.ycw.cebs.sys.service.ISysUserService;
import com.ycw.cebs.sys.vo.SysUserListVO;
import com.ycw.common.constants.CommonConstants;
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
@Service
public class SysUserServiceImpl extends ServiceImpl<ISysUserMapper, SysUser> implements ISysUserService {

	@Autowired
	private ISysUserMapper sysUserMapper;

	/**
	 * 用户列表分页查询
	 * @author yuminjun
	 * @date 2020/04/21 14:52:30
	 * @param sysUserListParamVO 查询参数
	 * @param pageParams 分页参数（为空时默认查询所有）
	 * @return
	 */
	@Override
	public List<SysUserListVO> queryUserList(SysUserListParam sysUserListParamVO, PageParams pageParams) {
		return sysUserMapper.querySysUserList(sysUserListParamVO);
	}

	/**
	 * 根据登录账号获取用户
	 * @author yuminjun
	 * @date 2020/04/21 17:33:46
	 * @param account 登录账号
	 * @return
	 */
	@Override
	public SysUser getUserByLoginAccount(String account) {
		LambdaQueryWrapper<SysUser> queryWrapper = Wrappers.lambdaQuery();
		queryWrapper.eq(SysUser::getLoginName, account).or()
					.eq(SysUser::getMobilePhone, account)
					.eq(SysUser::getClientId, ClientTypeEnum.BACK_END.getCode())
					.eq(SysUser::getDelInd, CommonConstants.INT_NO);
		SysUser user = sysUserMapper.selectOne(queryWrapper);
		return user;
	}

}
