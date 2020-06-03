package com.ycw.cebs.sys.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ycw.cebs.sys.entity.SysUser;
import com.ycw.cebs.sys.param.SysUserListParam;
import com.ycw.cebs.sys.vo.SysUserListVO;
import com.ycw.common.base.BaseCrudMapper;

/**
 * 系统用户Mapper接口
 * @author yuminjun
 * @date 2020/04/21 16:39:47
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
@Mapper
public interface SysUserMapper extends BaseCrudMapper<SysUser> {

	/**
	 * 系统用户列表查询
	 * @author yuminjun
	 * @date 2020/04/21 16:40:19
	 * @param sysUserListParam
	 * @return
	 */
	List<SysUserListVO> querySysUserList(@Param("params") SysUserListParam sysUserListParam);

}

