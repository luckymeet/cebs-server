package com.ycw.cebs.sys.api;

import com.github.pagehelper.PageInfo;
import com.ycw.cebs.sys.param.SysUserAddParam;
import com.ycw.cebs.sys.param.SysUserEditParam;
import com.ycw.cebs.sys.param.SysUserListParam;
import com.ycw.cebs.sys.vo.SysUserDetailVO;
import com.ycw.cebs.sys.vo.SysUserListVO;
import com.ycw.common.page.PageParams;
import com.ycw.common.response.ResponseVO;

/**
 * 系统用户Api接口
 * @author yuminjun
 * @date 2020/04/21 16:45:22
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
public interface ISysUserApi {

	/**
	 * 系统用户列表查询
	 * @author yuminjun
	 * @date 2020/04/21 16:50:16
	 * @param vo
	 * @param pageParams 分页参数（为null时默认查所有）
	 * @return
	 */
	ResponseVO<PageInfo<SysUserListVO>> queryUserPage(SysUserListParam vo, PageParams pageParams);

	/**
	 * 根据id查询系统用户数据
	 * @author yuminjun
	 * @date 2020/04/21 16:55:11
	 * @param id
	 * @return
	 */
	ResponseVO<SysUserDetailVO> getUser(Long id);

	/**
	 * 保存系统用户数据
	 * @author yuminjun
	 * @date 2020/04/21 16:55:37
	 * @param vo
	 * @return
	 */
	ResponseVO<Long> saveUser(SysUserAddParam vo);

	/**
	 * 修改系统用户数据
	 * @author yuminjun
	 * @date 2020/04/21 16:55:59
	 * @param vo
	 * @return
	 */
	ResponseVO<String> updateUser(SysUserEditParam vo);

	/**
	 * 删除系统用户数据
	 * @author yuminjun
	 * @date 2020/04/21 16:56:15
	 * @param id
	 * @return
	 */
	ResponseVO<String> deleteUser(Long id);

	/**
	 * 新增用户权限
	 * @author yuminjun
	 * @date 2020/05/13 16:12:06
	 * @param userId
	 * @param strings
	 * @return
	 */
	ResponseVO<String> saveUserPerm(Long userId, String[] strings);

	/**
	 * 修改用户权限
	 * @author yuminjun
	 * @date 2020/05/13 16:12:21
	 * @param userId
	 * @param strings
	 * @return
	 */
	ResponseVO<String> updateUserPerm(Long userId, String[] strings);

	/**
	 * 密码重置
	 * @author yuminjun
	 * @date 2020/05/20 14:48:43
	 * @param id 用户id
	 * @return
	 */
	ResponseVO<String> resetPassword(Long id);

	/**
	 * 密码修改
	 * @author yuminjun
	 * @date 2020/05/20 14:58:24
	 * @param password 新密码
	 * @return
	 */
	ResponseVO<String> updatePassword(String password);

	/**
	 * 用户状态修改
	 * @author yuminjun
	 * @date 2020/05/25 15:36:30
	 * @param id 用户id
	 * @param status 状态
	 * @return
	 */
	ResponseVO<String> changeStatus(Integer id, Integer status);

}
