package com.ycw.cebs.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ycw.cebs.user.entity.SysUserEntity;
import com.ycw.cebs.user.mapper.ISysUserMapper;
import com.ycw.cebs.user.service.ISysUserService;
import com.ycw.cebs.user.vo.SysUserDetailVO;
import com.ycw.cebs.user.vo.SysUserListVO;
import com.ycw.cebs.user.vo.param.SysUserListParamVO;
import com.ycw.cebs.user.vo.param.SysUserParamVO;
import com.ycw.common.exception.MsgException;
import com.ycw.common.page.PageParams;
import com.ycw.common.response.ResponseCode;
import com.ycw.common.utils.BeanHandleUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * @类名称 SysUserServiceImpl
 * @类描述 【系统用户表】Service接口实现类
 * @作者 yuminjun
 * @创建时间 2019年12月27日
 * @版本 v1.00
 * @修改记录
 *
 *       <pre>
 * 版本     		修改人 	修改时间    	 	修改内容	描述
 * ----------------------------------------------
 * 1.00 	yuminjun     	2019年12月27日 	新建
 * ----------------------------------------------
 *       </pre>
 */
@Service("sysUserService")
public class SysUserServiceImpl extends ServiceImpl<ISysUserMapper, SysUserEntity> implements ISysUserService {

	@Autowired
	private ISysUserMapper sysUserMapper;

	/**
	 * @方法名称 querySysUserPage
	 * @功能描述 【系统用户表】分页查询
	 * @作者 yuminjun
	 * @创建时间 2019年12月27日 下午5:49:58
	 * @param vo         查询参数
	 * @param pageParams 分页参数(为null时查询所有)
	 * @return
	 */
	@Override
	public List<SysUserListVO> querySysUserPage(SysUserListParamVO vo, PageParams pageParams) {
		return sysUserMapper.querySysUserList(vo);
	}

	/**
	 * @方法名称 findSysUserList
	 * @功能描述 【系统用户表】列表查询
	 * @作者 yuminjun
	 * @创建时间 2019年12月27日 下午5:49:58
	 * @param vo         查询参数
	 * @param pageParams 分页参数(为null时查询所有)
	 * @return
	 */
	@Override
	public List<SysUserListVO> querySysUserList(SysUserListParamVO vo) {
		return sysUserMapper.querySysUserList(vo);
	}

}
