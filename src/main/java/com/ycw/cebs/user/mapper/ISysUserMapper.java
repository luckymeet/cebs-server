package com.ycw.cebs.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ycw.cebs.user.entity.SysUserEntity;
import com.ycw.cebs.user.vo.SysUserListVO;
import com.ycw.cebs.user.vo.param.SysUserListParamVO;
import com.ycw.common.base.BaseCrudMapper;

/**
 * @类名称 SysUserMapper
 * @类描述 <pre> 【系统用户表】Mapper接口</pre>
 * @作者 yuminjun
 * @创建时间 2019年12月27日
 * @版本 v1.00
 * @修改记录 <pre>
 * 版本     		修改人 	修改时间    	 	修改内容	描述
 * ----------------------------------------------
 * 1.00 	yuminjun     	2019年12月27日 	新建
 * ----------------------------------------------
 * </pre>
 */
@Mapper
public interface ISysUserMapper extends BaseCrudMapper<SysUserEntity> {

	/**
	 * @方法名称 querySysUserList
	 * @功能描述 【系统用户表】列表查询
	 * @作者     yuminjun
	 * @创建时间 2019年12月27日 下午3:09:42
	 * @param sysUserListParamVO
	 * @return
	 */
	List<SysUserListVO> querySysUserList(@Param("params") SysUserListParamVO sysUserListParamVO);

}

