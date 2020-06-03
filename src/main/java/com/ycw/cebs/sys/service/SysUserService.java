package com.ycw.cebs.sys.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ycw.cebs.sys.entity.SysUser;
import com.ycw.cebs.sys.param.SysUserListParam;
import com.ycw.cebs.sys.vo.SysUserListVO;
import com.ycw.common.exception.SysException;
import com.ycw.common.page.PageParam;

/**
 * 系统用户Service接口
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
public interface SysUserService extends IService<SysUser>{

	/**
	 * 用户列表分页查询
	 * @author yuminjun
	 * @date 2020/04/21 14:52:30
	 * @param sysUserListParam 查询参数
	 * @param pageParam 分页参数（为空时默认查询所有）
	 * @return
	 */
	List<SysUserListVO> queryUserList(SysUserListParam sysUserListParam, PageParam pageParam);

	/**
	 * 根据登录令牌获取用户
	 * @author yuminjun
	 * @date 2020/04/21 17:33:46
	 * @param principal 登录令牌
	 * @return
	 */
	SysUser getUserByLoginAccount(String principal);

}
