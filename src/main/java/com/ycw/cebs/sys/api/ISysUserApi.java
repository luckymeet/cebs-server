package com.ycw.cebs.sys.api;

import com.github.pagehelper.PageInfo;
import com.ycw.cebs.sys.vo.SysUserDetailVO;
import com.ycw.cebs.sys.vo.SysUserListVO;
import com.ycw.cebs.sys.vo.param.SysUserListParamVO;
import com.ycw.cebs.sys.vo.param.SysUserAddParamVO;
import com.ycw.common.exception.SysException;
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
	 * @throws SysException
	 */
	ResponseVO<PageInfo<SysUserListVO>> queryUserPage(SysUserListParamVO vo, PageParams pageParams) throws SysException;

	/**
	 * 根据id查询系统用户数据
	 * @author yuminjun
	 * @date 2020/04/21 16:55:11
	 * @param id
	 * @return
	 * @throws SysException
	 */
	ResponseVO<SysUserDetailVO> getUser(Long id) throws SysException;

	/**
	 * 保存系统用户数据
	 * @author yuminjun
	 * @date 2020/04/21 16:55:37
	 * @param vo
	 * @return
	 */
	ResponseVO<Long> saveUser(SysUserAddParamVO vo) throws SysException;

	/**
	 * 修改系统用户数据
	 * @author yuminjun
	 * @date 2020/04/21 16:55:59
	 * @param vo
	 * @return
	 */
	ResponseVO<Long> updateUser(SysUserAddParamVO vo) throws SysException;

	/**
	 * 删除系统用户数据
	 * @author yuminjun
	 * @date 2020/04/21 16:56:15
	 * @param id
	 * @return
	 * @throws SysException
	 */
	ResponseVO<String> deleteUser(Long id) throws SysException;

}