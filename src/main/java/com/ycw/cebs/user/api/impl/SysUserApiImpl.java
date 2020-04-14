package com.ycw.cebs.user.api.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.ycw.cebs.user.api.ISysUserApi;
import com.ycw.cebs.user.entity.SysUserEntity;
import com.ycw.cebs.user.service.ISysUserService;
import com.ycw.cebs.user.vo.SysUserDetailVO;
import com.ycw.cebs.user.vo.SysUserListVO;
import com.ycw.cebs.user.vo.param.SysUserListParamVO;
import com.ycw.cebs.user.vo.param.SysUserParamVO;
import com.ycw.common.exception.MsgException;
import com.ycw.common.page.PageParams;
import com.ycw.common.response.ResponseCode;
import com.ycw.common.response.ResponseVO;
import com.ycw.common.utils.BeanHandleUtils;

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
@Service("sysUserApi")
public class SysUserApiImpl implements ISysUserApi {

	@Autowired
	private ISysUserService sysUserService;

	/**
	 * @方法名称 querySysUserPage
	 * @功能描述 【系统用户表】分页查询
	 * @作者 yuminjun
	 * @创建时间 2019年12月27日 下午5:49:58
	 * @param vo         查询参数
	 * @param pageParams 分页参数(为null时查询所有)
	 * @return
	 * @throws MsgException
	 */
	@Override
	public ResponseVO<PageInfo<SysUserListVO>> querySysUserPage(SysUserListParamVO vo, PageParams pageParams) throws MsgException {
		List<SysUserListVO> page = sysUserService.querySysUserList(vo);
		return ResponseVO.success(new PageInfo<>(page));
	}

	/**
	 * @方法名称 findSysUserList
	 * @功能描述 【系统用户表】列表查询
	 * @作者 yuminjun
	 * @创建时间 2019年12月27日 下午5:49:58
	 * @param vo         查询参数
	 * @param pageParams 分页参数(为null时查询所有)
	 * @return
	 * @throws MsgException
	 */
	@Override
	public ResponseVO<List<SysUserListVO>> querySysUserList(SysUserListParamVO vo) throws MsgException {
		List<SysUserListVO> list = sysUserService.querySysUserList(vo);
		return ResponseVO.success(list);
	}

	/**
	 * @方法名称 getSysUser
	 * @功能描述 根据id查询【系统用户表】数据
	 * @作者 yuminjun
	 * @创建时间 2019年12月27日 14:17
	 * @param id 主键id
	 * @return
	 * @throws MsgException
	 */
	@Override
	public ResponseVO<SysUserDetailVO> getSysUser(Long id) throws MsgException {
		if (id == null) {
			throw new MsgException(ResponseCode.ERR_418.getCode(), "id不能为空");
		}
		SysUserEntity sysUser = sysUserService.getById(id);
		SysUserDetailVO vo = BeanHandleUtils.beanCopy(sysUser, SysUserDetailVO.class);
		return ResponseVO.success(vo);
	}

	/**
	 * @方法名称 saveSysUser
	 * @功能描述 保存【系统用户表】数据
	 * @作者 yuminjun
	 * @创建时间 2019年12月27日 上午10:25:18
	 * @param vo
	 * @return
	 */
	@Override
	public ResponseVO<Long> saveSysUser(SysUserParamVO vo) {
		SysUserEntity sysUser = BeanHandleUtils.beanCopy(vo, SysUserEntity.class);
		sysUserService.save(sysUser);
		return ResponseVO.success(sysUser.getId(), "新增成功");
	}

	/**
	 * @方法名称 updateSysUser
	 * @功能描述 修改【系统用户表】数据
	 * @作者 yuminjun
	 * @创建时间 2019年12月27日 上午10:35:08
	 * @param vo
	 * @return
	 */
	@Override
	public ResponseVO<Long> updateSysUser(SysUserParamVO vo) {
		SysUserEntity sysUser = BeanHandleUtils.beanCopy(vo, SysUserEntity.class);
		sysUserService.updateById(sysUser);
		return ResponseVO.success(sysUser.getId(), "修改成功");
	}

	/**
	 * @方法名称 deleteSysUser
	 * @功能描述 删除【系统用户表】数据
	 * @作者 yuminjun
	 * @创建时间 2019年12月27日 上午10:53:11
	 * @param id
	 * @return
	 * @throws MsgException
	 */
	@Override
	public ResponseVO<String> deleteSysUser(Long id) throws MsgException {
		if (id == null) {
			throw new MsgException(ResponseCode.ERR_418.getCode(), "id不能为空");
		}
		sysUserService.removeById(id);
		return ResponseVO.success(null, "删除成功");
	}

}
