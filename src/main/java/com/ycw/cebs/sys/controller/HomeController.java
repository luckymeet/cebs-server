package com.ycw.cebs.sys.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ycw.cebs.common.constants.sys.MenuTypeEnum;
import com.ycw.cebs.sys.api.ISysMenuApi;
import com.ycw.cebs.sys.vo.param.LoginParamVO;
import com.ycw.common.response.ResponseCode;
import com.ycw.common.response.ResponseVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/sys")
public class HomeController {

	@Autowired
	private ISysMenuApi sysMenuApi;

	/**
	 * 未授权
	 * @author yuminjun
	 * @date 2020/04/23 15:38:49
	 * @return
	 */
	@GetMapping("/unauthorized")
	public ResponseVO<String> unauthorized() {
		log.info("用户请求需要身份验证");
		return ResponseVO.fail(ResponseCode.ERR_401.getCode(), ResponseCode.ERR_401.getDesc());
	}

	/**
	 * 登录
	 * @author yuminjun
	 * @date 2020/04/23 15:38:41
	 * @param loginParamVO
	 * @return
	 */
	@PostMapping("/login")
	public ResponseVO<String> login(@Validated LoginParamVO loginParamVO) {
		Subject user = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(loginParamVO.getUsername(), loginParamVO.getPassword());
		try {
			// 跳入入自己实现的域即shiroRealm中验证
			user.login(token);
			return ResponseVO.success("登录成功");
		} catch (UnknownAccountException e) {
			return ResponseVO.fail(ResponseCode.ERR_LOGIN.getCode(), "账号不存在");
		} catch (IncorrectCredentialsException e) {
			return ResponseVO.fail(ResponseCode.ERR_LOGIN.getCode(), "密码不正确");
		} catch (LockedAccountException e) {
			return ResponseVO.fail(ResponseCode.ERR_LOGIN.getCode(), "账号已锁定, 请联系管理员");
		} catch (DisabledAccountException e) {
			return ResponseVO.fail(ResponseCode.ERR_LOGIN.getCode(), "账号未启用");
		} catch (AuthenticationException e) {
			return ResponseVO.fail(ResponseCode.ERR_LOGIN.getCode(), "登录失败：" + e.getMessage());
		} catch (Exception e) {
			return ResponseVO.fail(ResponseCode.ERR_999.getCode(), "未知错误, 请联系管理员");
		}
	}

	/**
	 * 注销登录
	 * @author yuminjun
	 * @date 2020/04/23 15:38:31
	 * @param request
	 * @return
	 */
	@GetMapping("/logout")
	public ResponseVO<String> logout(HttpServletRequest request) {
		Subject subject = SecurityUtils.getSubject();
		if (subject != null) {
			subject.logout();
		}
		return ResponseVO.success("注销成功");
	}

	/**
	 * 查询当前用户菜单路由权限
	 * @author yuminjun
	 * @date 2020/04/23 15:34:29
	 * @return
	 */
	@GetMapping("/routes/menu")
	public ResponseVO<List<String>> queryMenuPerm() {
		ResponseVO<List<String>> routeList = sysMenuApi.queryRouteList(MenuTypeEnum.MENU.getCode());
		return routeList;
	}

	/**
	 * 查询当前用户按钮路由权限
	 * @author yuminjun
	 * @date 2020/04/23 15:34:44
	 * @return
	 */
	@GetMapping("/routes/button")
	public ResponseVO<List<String>> queryButtonPerm() {
		ResponseVO<List<String>> routeList = sysMenuApi.queryRouteList(MenuTypeEnum.BUTTON.getCode());
		return routeList;
	}

}
