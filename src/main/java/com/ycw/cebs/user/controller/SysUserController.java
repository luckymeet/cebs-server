package com.ycw.cebs.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.ycw.cebs.user.api.ISysUserApi;
import com.ycw.cebs.user.vo.SysUserDetailVO;
import com.ycw.cebs.user.vo.SysUserListVO;
import com.ycw.cebs.user.vo.param.SysUserListParamVO;
import com.ycw.cebs.user.vo.param.SysUserParamVO;
import com.ycw.common.base.BaseController;
import com.ycw.common.exception.MsgException;
import com.ycw.common.page.PageParams;
import com.ycw.common.response.ResponseVO;

/**
 * @类名称 SysUserController.java
 * @类描述  用户信息Controller
 * @作者 yuminjun yuminjun@lexiangbao.com
 * @创建时间 2019年12月27日 下午12:19:16
 * @版本 1.00
 *
 * @修改记录
 *
 *       <pre>
 *     版本                       修改人 		修改日期 		 修改内容描述
 *     ----------------------------------------------
 *     1.00 	yuminjun 	2019年12月27日
 *     ----------------------------------------------
 *       </pre>
 */
@RestController
@RequestMapping("/sys")
public class SysUserController extends BaseController {

	@Autowired
	private ISysUserApi sysUserApi;

	/**
	 * @方法名称 findSysUserPage
	 * @功能描述 用户信息分页查询
	 * @作者 yuminjun
	 * @创建时间 2019年12月27日 下午12:18:17
	 * @param vo         查询参数
	 * @param pageParams 分页参数(为null时查询所有)
	 * @return
	 * @throws MsgException
	 */
	@GetMapping("/user/list")
	public ResponseVO<PageInfo<SysUserListVO>> findSysUserPage(SysUserListParamVO vo, PageParams pageParams) throws MsgException {
		return sysUserApi.querySysUserPage(vo, pageParams);
	}

	/**
	 * @方法名称 getSysUser
	 * @功能描述 获取用户基本
	 * @作者 yuminjun
	 * @创建时间 2019年12月27日 下午12:20:28
	 * @param id
	 * @return
	 * @throws MsgException
	 */
	@GetMapping("/user")
	public ResponseVO<SysUserDetailVO> getSysUser(Long id) throws MsgException {
		return sysUserApi.getSysUser(id);
	}

	/**
	 * @方法名称 saveOrUpdateSysUser
	 * @功能描述 保存/修改用户数据
	 * @作者 yuminjun
	 * @创建时间 2019年12月27日 上午10:25:18
	 * @param vo
	 * @return
	 * @throws MsgException
	 */
	@PostMapping("/user")
	public ResponseVO<Long> saveOrUpdateSysUser(@Validated SysUserParamVO vo) throws MsgException {
		Long id = vo.getId();
		if (null == id) {
			return sysUserApi.saveSysUser(vo);
		} else {
			return sysUserApi.updateSysUser(vo);
		}
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
	@DeleteMapping("/user")
	public ResponseVO<String> deleteSysUser(Long id) throws MsgException {
		return sysUserApi.deleteSysUser(id);
	}

}
