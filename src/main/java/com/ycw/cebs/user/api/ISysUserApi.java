package com.ycw.cebs.user.api;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.ycw.cebs.user.vo.SysUserDetailVO;
import com.ycw.cebs.user.vo.SysUserListVO;
import com.ycw.cebs.user.vo.param.SysUserListParamVO;
import com.ycw.cebs.user.vo.param.SysUserParamVO;
import com.ycw.common.exception.MsgException;
import com.ycw.common.page.PageParams;
import com.ycw.common.response.ResponseVO;

/**
 * @类名称 SysUserService
 * @类描述
 *
 *      <pre>
 * 【系统用户表】Service接口
 *      </pre>
 *
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
public interface ISysUserApi {

	/**
	 * @方法名称 querySysUserPage
	 * @功能描述 【系统用户表】分页查询
	 * @作者 yuminjun
	 * @创建时间 2019年12月27日 下午5:49:58
	 * @param vo         查询参数
	 * @param pageParams 分页参数(为null时查询所有)
	 * @return
	 */
	ResponseVO<PageInfo<SysUserListVO>> querySysUserPage(SysUserListParamVO vo, PageParams pageParams) throws MsgException;

	/**
	 * @方法名称 findSysUserList
	 * @功能描述 【系统用户表】列表查询
	 * @作者 yuminjun
	 * @创建时间 2019年12月27日 下午5:49:58
	 * @param vo         查询参数
	 * @param pageParams 分页参数(为null时查询所有)
	 * @return
	 */
	ResponseVO<List<SysUserListVO>> querySysUserList(SysUserListParamVO vo) throws MsgException;

	/**
	 * @方法名称 getSysUser
	 * @功能描述 根据id查询【系统用户表】数据
	 * @作者 yuminjun
	 * @创建时间 2019年12月27日 14:17
	 * @param id 主键id
	 * @return
	 * @throws MsgException
	 */
	ResponseVO<SysUserDetailVO> getSysUser(Long id) throws MsgException;

	/**
	 * @方法名称 saveSysUser
	 * @功能描述 保存【系统用户表】数据
	 * @作者 yuminjun
	 * @创建时间 2019年12月27日 上午10:25:18
	 * @param vo
	 * @return
	 */
	ResponseVO<Long> saveSysUser(SysUserParamVO vo) throws MsgException;

	/**
	 * @方法名称 updateSysUser
	 * @功能描述 修改【系统用户表】数据
	 * @作者 yuminjun
	 * @创建时间 2019年12月27日 上午10:35:08
	 * @param vo
	 * @return
	 */
	ResponseVO<Long> updateSysUser(SysUserParamVO vo) throws MsgException;

	/**
	 * @方法名称 deleteSysUser
	 * @功能描述 删除【系统用户表】数据
	 * @作者 yuminjun
	 * @创建时间 2019年12月27日 上午10:53:11
	 * @param id
	 * @return
	 * @throws MsgException
	 */
	ResponseVO<String> deleteSysUser(Long id) throws MsgException;

}
