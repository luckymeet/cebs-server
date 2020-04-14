package com.ycw.cebs.user.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ycw.cebs.user.entity.SysUserEntity;
import com.ycw.cebs.user.vo.SysUserListVO;
import com.ycw.cebs.user.vo.param.SysUserListParamVO;
import com.ycw.common.exception.SysException;
import com.ycw.common.page.PageParams;

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
public interface ISysUserService extends IService<SysUserEntity>{

	/**
	 * @方法名称 querySysUserPage
	 * @功能描述 【系统用户表】分页查询
	 * @作者 yuminjun
	 * @创建时间 2019年12月27日 下午5:49:58
	 * @param vo         查询参数
	 * @param pageParams 分页参数(为null时查询所有)
	 * @return
	 */
	List<SysUserListVO> querySysUserPage(SysUserListParamVO vo, PageParams pageParams) throws SysException;

	/**
	 * @方法名称 findSysUserList
	 * @功能描述 【系统用户表】列表查询
	 * @作者 yuminjun
	 * @创建时间 2019年12月27日 下午5:49:58
	 * @param vo         查询参数
	 * @param pageParams 分页参数(为null时查询所有)
	 * @return
	 */
	List<SysUserListVO> querySysUserList(SysUserListParamVO vo) throws SysException;

}
