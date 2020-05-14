package com.ycw.cebs.sys.controller;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.ycw.cebs.common.utils.SessionUtil;
import com.ycw.cebs.sys.api.ISysUserApi;
import com.ycw.cebs.sys.vo.SysUserDetailVO;
import com.ycw.cebs.sys.vo.SysUserListVO;
import com.ycw.cebs.sys.vo.param.SysUserAddParamVO;
import com.ycw.cebs.sys.vo.param.SysUserEditParamVO;
import com.ycw.cebs.sys.vo.param.SysUserListParamVO;
import com.ycw.common.exception.SysException;
import com.ycw.common.page.PageParams;
import com.ycw.common.response.ResponseCode;
import com.ycw.common.response.ResponseVO;

/**
 * 用户管理Controller
 * @author yuminjun
 * @date 2020/04/22 14:13:24
 * @version 1.00
 *
 * @record
 * <pre>
 * version  author      date          desc
 * -------------------------------------------------
 * 1.00     yuminjun    2020/04/22    新建
 * -------------------------------------------------
 * </pre>
 */
@RestController
@RequestMapping("/sys/user")
public class SysUserController {

	@Autowired
	private ISysUserApi sysUserApi;

	/**
	 * 用户列表分页查询
	 * @author yuminjun
	 * @date 2020/04/22 14:13:35
	 * @param vo 查询参数
	 * @param pageParams 分页参数
	 * @return
	 * @throws SysException
	 */
	@GetMapping("/list")
	@RequiresPermissions("sys:user:list")
	public ResponseVO<PageInfo<SysUserListVO>> queryUserPage(SysUserListParamVO vo, PageParams pageParams) {
		return sysUserApi.queryUserPage(vo, pageParams);
	}

	/**
	 * 用户详情
	 * @author yuminjun
	 * @date 2020/04/22 14:13:50
	 * @param id
	 * @return
	 * @throws SysException
	 */
	@GetMapping
	public ResponseVO<SysUserDetailVO> getUser(Long id) {
		return sysUserApi.getUser(id);
	}

	/**
	 * 用户新增
	 * @author yuminjun
	 * @date 2020/04/22 14:24:46
	 * @param vo
	 * @return
	 * @throws SysException
	 */
	@PostMapping
	public ResponseVO<Long> saveUser(@Validated SysUserAddParamVO vo) {
		ResponseVO<Long> saveUserResult = sysUserApi.saveUser(vo);
		ResponseVO<String> saveUserPermResult = sysUserApi.saveUserPerm(saveUserResult.getData(), StringUtils.split(vo.getPermIds(), ","));
		if (!saveUserPermResult.isSuccess()) {
			return ResponseVO.fail(ResponseCode.ERR_SAVE.getCode(), "用户权限新增失败");
		}
		return saveUserResult;
	}

	/**
	 * 用户修改
	 * @author yuminjun
	 * @date 2020/04/22 14:24:57
	 * @param vo
	 * @return
	 * @throws SysException
	 */
	@PutMapping
	public ResponseVO<Long> updateUser(@Validated SysUserEditParamVO vo) {
		ResponseVO<Long> updateUserResult = sysUserApi.updateUser(vo);
		ResponseVO<String> updateUserPermResult = sysUserApi.updateUserPerm(vo.getId(), StringUtils.split(vo.getPermIds(), ","));
		if (!updateUserPermResult.isSuccess()) {
			return ResponseVO.fail(ResponseCode.ERR_SAVE.getCode(), "用户权限修改失败");
		}
		return updateUserResult;
	}

	/**
	 * 用户删除
	 * @author yuminjun
	 * @date 2020/04/22 14:14:40
	 * @param id 用户id
	 * @return
	 * @throws SysException
	 */
	@DeleteMapping
	public ResponseVO<String> deleteUser(Long id) {
		return sysUserApi.deleteUser(id);
	}

}
