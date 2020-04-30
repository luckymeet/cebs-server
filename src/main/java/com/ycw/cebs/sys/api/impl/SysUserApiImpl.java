package com.ycw.cebs.sys.api.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.ycw.cebs.sys.api.ISysUserApi;
import com.ycw.cebs.sys.entity.SysUserEntity;
import com.ycw.cebs.sys.entity.SysUserPermEntity;
import com.ycw.cebs.sys.service.ISysPermService;
import com.ycw.cebs.sys.service.ISysUserService;
import com.ycw.cebs.sys.vo.SysUserDetailVO;
import com.ycw.cebs.sys.vo.SysUserListVO;
import com.ycw.cebs.sys.vo.param.SysUserAddParamVO;
import com.ycw.cebs.sys.vo.param.SysUserEditParamVO;
import com.ycw.cebs.sys.vo.param.SysUserListParamVO;
import com.ycw.common.exception.SysException;
import com.ycw.common.page.PageParams;
import com.ycw.common.response.ResponseCode;
import com.ycw.common.response.ResponseVO;
import com.ycw.common.utils.BeanHandleUtils;

/**
 * 系统用户Api接口实现类
 * @author yuminjun
 * @date 2020/04/21 17:25:56
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
@Service("sysUserApi")
public class SysUserApiImpl implements ISysUserApi {

	@Autowired
	private ISysUserService sysUserService;

	@Autowired
	private ISysPermService sysPermService;

	/**
	 * 用户列表分页查询
	 * @author yuminjun
	 * @date 2020/04/21 14:52:30
	 * @param vo 查询参数
	 * @param pageParams 分页参数（为空时默认查询所有）
	 * @return
	 */
	@Override
	public ResponseVO<PageInfo<SysUserListVO>> queryUserPage(SysUserListParamVO vo, PageParams pageParams) {
		List<SysUserListVO> page = this.sysUserService.queryUserList(vo, pageParams);
		return ResponseVO.success(new PageInfo<>(page));
	}

	/**
	 * 根据id查询系统用户数据
	 * @author yuminjun
	 * @date 2020/04/21 16:55:11
	 * @param id
	 * @return
	 */
	@Override
	public ResponseVO<SysUserDetailVO> getUser(Long id) {
		if (id == null) {
			throw new SysException(ResponseCode.ERR_418.getCode(), "id不能为空");
		}
		SysUserEntity sysUser = this.sysUserService.getById(id);
		List<SysUserPermEntity> userPerm = this.sysPermService.queryUserPermByUserId(id);
		SysUserDetailVO vo = BeanHandleUtils.beanCopy(sysUser, SysUserDetailVO.class);
		String permIds = StringUtils.join(userPerm.stream().map(SysUserPermEntity::getPermId).toArray());
		vo.setPermIds(permIds);
		return ResponseVO.success(vo);
	}

	/**
	 * 保存系统用户数据
	 * @author yuminjun
	 * @date 2020/04/21 16:55:37
	 * @param vo
	 * @return
	 */
	@Override
	public ResponseVO<Long> saveUser(SysUserAddParamVO vo) {
		String idCard = vo.getIdCard();
		if (StringUtils.isNotEmpty(idCard) && idCard.matches("/^[1-9]\\d{5}(18|19|20|(3\\d))\\d{2}((0[1-9])|(1[0-2]))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$/")) {
			throw new SysException(ResponseCode.ERR_417.getCode(), "请输入正确的身份证格式");
		}
		SysUserEntity user = BeanHandleUtils.beanCopy(vo, SysUserEntity.class);
		this.sysUserService.save(user);
		return ResponseVO.success(user.getId(), "新增成功");
	}

	/**
	 * 修改系统用户数据
	 * @author yuminjun
	 * @date 2020/04/21 16:55:59
	 * @param vo
	 * @return
	 */
	@Override
	public ResponseVO<Long> updateUser(SysUserEditParamVO vo) {
		String idCard = vo.getIdCard();
		if (StringUtils.isNotEmpty(idCard) && idCard.matches("/^[1-9]\\d{5}(18|19|20|(3\\d))\\d{2}((0[1-9])|(1[0-2]))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$/")) {
			throw new SysException(ResponseCode.ERR_417.getCode(), "请输入正确的身份证格式");
		}
		this.sysUserService.lambdaUpdate()
			.set(SysUserEntity::getUserNum, vo.getUserNum())
			.set(SysUserEntity::getRealName, vo.getRealName())
			.set(SysUserEntity::getNickName, vo.getNickName())
			.set(SysUserEntity::getLoginName, vo.getLoginName())
			.set(SysUserEntity::getProfilePhotoUrl, vo.getProfilePhotoUrl())
			.set(SysUserEntity::getSex, vo.getSex())
			.set(SysUserEntity::getBirthday, vo.getBirthday())
			.set(SysUserEntity::getMobilePhone, vo.getMobilePhone())
			.set(SysUserEntity::getEMail, vo.getEMail())
			.set(SysUserEntity::getIdCard, vo.getIdCard())
			.set(SysUserEntity::getQq, vo.getQq())
			.set(SysUserEntity::getWechat, vo.getWechat())
			.eq(SysUserEntity::getId, vo.getId());
		return ResponseVO.success(vo.getId(), "修改成功");
	}

	/**
	 * 删除系统用户数据
	 * @author yuminjun
	 * @date 2020/04/21 16:56:15
	 * @param id
	 * @return
	 */
	@Override
	public ResponseVO<String> deleteUser(Long id) {
		if (id == null) {
			throw new SysException(ResponseCode.ERR_418.getCode(), "id不能为空");
		}
		this.sysUserService.removeById(id);
		return ResponseVO.success(null, "删除成功");
	}

}
