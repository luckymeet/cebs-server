package com.ycw.cebs.sys.api.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.pagehelper.PageInfo;
import com.ycw.cebs.common.constant.sys.ClientTypeEnum;
import com.ycw.cebs.common.utils.PasswordUtil;
import com.ycw.cebs.common.utils.SessionUtil;
import com.ycw.cebs.sys.api.ISysUserApi;
import com.ycw.cebs.sys.entity.SysUser;
import com.ycw.cebs.sys.entity.SysUserPerm;
import com.ycw.cebs.sys.param.SysUserAddParam;
import com.ycw.cebs.sys.param.SysUserEditParam;
import com.ycw.cebs.sys.param.SysUserListParam;
import com.ycw.cebs.sys.service.ISysPermService;
import com.ycw.cebs.sys.service.ISysUserPermService;
import com.ycw.cebs.sys.service.ISysUserService;
import com.ycw.cebs.sys.vo.SysUserDetailVO;
import com.ycw.cebs.sys.vo.SysUserListVO;
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
@Service
public class SysUserApiImpl implements ISysUserApi {

	@Autowired
	private ISysUserService sysUserService;

	@Autowired
	private ISysPermService sysPermService;

	@Autowired
	private ISysUserPermService sysUserPermService;

	@Autowired
	private static final String DEFAULT_PASSWORD = "123456";

	private static final String ID_CARD_REGEX = "^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$";

	/**
	 * 用户列表分页查询
	 * @author yuminjun
	 * @date 2020/04/21 14:52:30
	 * @param vo 查询参数
	 * @param pageParams 分页参数（为空时默认查询所有）
	 * @return
	 */
	@Override
	public ResponseVO<PageInfo<SysUserListVO>> queryUserPage(SysUserListParam vo, PageParams pageParams) {
		vo.setCurUserId(SessionUtil.getCurrentUserId());
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
		SysUser sysUser = this.sysUserService.getById(id);
		List<SysUserPerm> userPerm = this.sysPermService.queryUserPermByUserId(id);
		String permIds = StringUtils.join(userPerm.stream().map(SysUserPerm::getPermId).toArray(), ",");
		SysUserDetailVO vo = BeanHandleUtils.beanCopy(sysUser, SysUserDetailVO.class);
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
	public ResponseVO<Long> saveUser(SysUserAddParam vo) {
		String idCard = vo.getIdCard();
		if (StringUtils.isNotEmpty(idCard) && !idCard.matches(ID_CARD_REGEX)) {
			throw new SysException(ResponseCode.ERR_417.getCode(), "请输入正确的身份证格式");
		}
		SysUser currentUser = SessionUtil.getCurrentUser();
		SysUser user = BeanHandleUtils.beanCopy(vo, SysUser.class);
		String salt = PasswordUtil.generateCredentialsSalt();
		user.setSalt(salt);
		user.setPassword(PasswordUtil.encryptPasswordMD5(DEFAULT_PASSWORD, salt));
		user.setClientId(ClientTypeEnum.BACK_END.getCode());
		user.setCreateChain(StringUtils.join(currentUser.getCreateChain(), currentUser.getId(), ","));
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
	public ResponseVO<String> updateUser(SysUserEditParam vo) {
		String idCard = vo.getIdCard();
		if (StringUtils.isNotEmpty(idCard) && !idCard.matches(ID_CARD_REGEX)) {
			throw new SysException(ResponseCode.ERR_417.getCode(), "请输入正确的身份证格式");
		}
		this.sysUserService.lambdaUpdate()
			.set(SysUser::getUserNum, vo.getUserNum())
			.set(SysUser::getRealName, vo.getRealName())
			.set(SysUser::getNickName, vo.getNickName())
			.set(SysUser::getLoginName, vo.getLoginName())
			.set(SysUser::getProfilePhotoUrl, vo.getProfilePhotoUrl())
			.set(SysUser::getSex, vo.getSex())
			.set(SysUser::getBirthday, vo.getBirthday())
			.set(SysUser::getMobilePhone, vo.getMobilePhone())
			.set(SysUser::getEMail, vo.getEMail())
			.set(SysUser::getIdCard, idCard)
			.set(SysUser::getQq, vo.getQq())
			.set(SysUser::getWechat, vo.getWechat())
			.eq(SysUser::getId, vo.getId())
			.update();
		return ResponseVO.success(null, "修改成功");
	}

	/**
	 * 新增用户权限
	 * @author yuminjun
	 * @date 2020/05/13 16:12:06
	 * @param userId
	 * @param permIdArray
	 */
	@Override
	public ResponseVO<String> saveUserPerm(Long userId, String[] permIdArray) {
		if (null == userId || null == permIdArray) {
			return ResponseVO.success(null);
		}
		List<SysUserPerm> userPermList = new ArrayList<>();
		for (int i = 0; i < permIdArray.length; i++) {
			SysUserPerm userPerm = new SysUserPerm();
			userPerm.setPermId(Long.parseLong(permIdArray[i]));
			userPerm.setUserId(userId);
			userPermList.add(userPerm);
		}
		sysUserPermService.saveBatch(userPermList);
		return ResponseVO.success(null, "新增用户权限成功");
	}

	/**
	 * 修改用户权限
	 * @author yuminjun
	 * @date 2020/05/13 16:12:21
	 * @param userId
	 * @param permIdArray
	 */
	@Override
	public 	ResponseVO<String> updateUserPerm(Long userId, String[] permIdArray) {
		if (null == userId || null == permIdArray) {
			return ResponseVO.success(null);
		}
		List<SysUserPerm> userPermList = this.sysPermService.queryUserPermByUserId(userId);
		Set<Long> oldPermIdSet = userPermList.stream().map(SysUserPerm::getPermId).collect(Collectors.toSet());
		Set<Long> newPermIdSet = Arrays.asList(permIdArray).stream().map(permId -> Long.parseLong(permId)).collect(Collectors.toSet());
		Set<Long> addSet = new HashSet<>(newPermIdSet);
		Set<Long> deleteSet = new HashSet<>(oldPermIdSet);
		// 差集=需要新增的权限
		addSet.removeAll(oldPermIdSet);
		// 差集=需要删除的权限
		deleteSet.removeAll(newPermIdSet);

		/* 新增新的权限 */
		List<SysUserPerm> addUserPermList = new ArrayList<>();
		for (Long permId : addSet) {
			SysUserPerm userPerm = new SysUserPerm();
			userPerm.setPermId(permId);
			userPerm.setUserId(userId);
			addUserPermList.add(userPerm);
		}
		sysUserPermService.saveBatch(addUserPermList);

		/* 删除去掉的权限 */
		if (CollectionUtils.isNotEmpty(deleteSet)) {
			LambdaQueryWrapper<SysUserPerm> queryWrapper = Wrappers.lambdaQuery();
			queryWrapper.eq(SysUserPerm::getUserId, userId);
			queryWrapper.in(SysUserPerm::getPermId, deleteSet);
			sysUserPermService.remove(queryWrapper);
		}
		return ResponseVO.success(null, "修改用户权限成功");
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

	/**
	 * 密码重置
	 * @author yuminjun
	 * @date 2020/05/20 14:48:43
	 * @param id 用户id
	 * @return
	 */
	@Override
	public ResponseVO<String> resetPassword(Long id) {
		String salt = PasswordUtil.generateCredentialsSalt();
		this.sysUserService.lambdaUpdate()
			.set(SysUser::getSalt, salt)
			.set(SysUser::getPassword, PasswordUtil.encryptPasswordMD5(DEFAULT_PASSWORD, salt))
			.eq(SysUser::getId, id).update();
		return ResponseVO.success(null, "重置成功");
	}

	/**
	 * 密码修改
	 * @author yuminjun
	 * @date 2020/05/20 14:58:24
	 * @param password 新密码
	 * @return
	 */
	@Override
	public ResponseVO<String> updatePassword(String password) {
		String salt = PasswordUtil.generateCredentialsSalt();
		this.sysUserService.lambdaUpdate()
			.set(SysUser::getSalt, salt)
			.set(SysUser::getPassword, PasswordUtil.encryptPasswordMD5(password, salt))
			.eq(SysUser::getId, SessionUtil.getCurrentUserId()).update();
		return ResponseVO.success(null, "修改成功");
	}

}
