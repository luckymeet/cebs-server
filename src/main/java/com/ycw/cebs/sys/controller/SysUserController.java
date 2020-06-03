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
import com.ycw.cebs.sys.api.SysUserApi;
import com.ycw.cebs.sys.param.SysUserAddParam;
import com.ycw.cebs.sys.param.SysUserEditParam;
import com.ycw.cebs.sys.param.SysUserListParam;
import com.ycw.cebs.sys.vo.SysUserDetailVO;
import com.ycw.cebs.sys.vo.SysUserListVO;
import com.ycw.common.page.PageParam;
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
	private SysUserApi sysUserApi;

	/**
	 * 用户列表分页查询
	 * @author yuminjun
	 * @date 2020/04/22 14:13:35
	 * @param vo 查询参数
	 * @param pageParam 分页参数
	 * @return
	 */
	@GetMapping("/list")
	@RequiresPermissions("sys:user:list")
	public ResponseVO<PageInfo<SysUserListVO>> queryUserPage(SysUserListParam vo, PageParam pageParam) {
		return sysUserApi.queryUserPage(vo, pageParam);
	}

	/**
	 * 用户详情
	 * @author yuminjun
	 * @date 2020/04/22 14:13:50
	 * @param id
	 * @return
	 */
	@GetMapping
	@RequiresPermissions("sys:user:edit")
	public ResponseVO<SysUserDetailVO> getUserForUpdate(Long id) {
		return sysUserApi.getUser(id);
	}

	/**
	 * 用户详情
	 * @author yuminjun
	 * @date 2020/04/22 14:13:50
	 * @param id
	 * @return
	 */
	@GetMapping("/view")
	@RequiresPermissions("sys:user:view")
	public ResponseVO<SysUserDetailVO> getUserForView(Long id) {
		return sysUserApi.getUser(id);
	}

	/**
	 * 用户新增
	 * @author yuminjun
	 * @date 2020/04/22 14:24:46
	 * @param vo
	 * @return
	 */
	@PostMapping
	@RequiresPermissions("sys:user:add")
	public ResponseVO<Long> saveUser(@Validated SysUserAddParam vo) {
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
	 */
	@PutMapping
	@RequiresPermissions("sys:user:edit")
	public ResponseVO<String> updateUser(@Validated SysUserEditParam vo) {
		ResponseVO<String> updateUserResult = sysUserApi.updateUser(vo);
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
	 */
	@DeleteMapping
	@RequiresPermissions("sys:user:delete")
	public ResponseVO<String> deleteUser(Long id) {
		return sysUserApi.deleteUser(id);
	}

	/**
	 * 密码重置
	 * @author yuminjun
	 * @date 2020/04/22 14:14:40
	 * @param id 用户id
	 * @return
	 */
	@PutMapping("/password/reset")
	@RequiresPermissions("sys:user:password:reset")
	public ResponseVO<String> resetPassword(Long id) {
		return sysUserApi.resetPassword(id);
	}

	/**
	 * 密码修改
	 * @author yuminjun
	 * @date 2020/05/20 14:58:24
	 * @param password 新密码
	 * @return
	 */
	@PutMapping("/password/update")
	public ResponseVO<String> updatePassword(String password) {
		return sysUserApi.updatePassword(password);
	}

	/**
	 * 用户状态修改
	 * @author yuminjun
	 * @date 2020/05/25 15:36:30
	 * @param id 用户id
	 * @param status 状态
	 * @return
	 */
	@PutMapping("/status/change")
	@RequiresPermissions("sys:user:status:change")
	public ResponseVO<String> changeStatus(Integer id, Integer status) {
		return sysUserApi.changeStatus(id, status);
	}

}
