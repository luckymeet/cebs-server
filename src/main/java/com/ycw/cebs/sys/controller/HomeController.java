package com.ycw.cebs.sys.controller;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ycw.common.response.ResponseCode;
import com.ycw.common.response.ResponseVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/sys")
public class HomeController {

	@GetMapping("/unauthorized")
	public ResponseVO<String> unauthorized() {
		log.info("用户请求需要身份验证");
		return ResponseVO.fail(ResponseCode.ERR_401.getCode(), ResponseCode.ERR_401.getDesc());
	}

	@PostMapping("/login")
	public ResponseVO<String> login(String username, String password) {
		if (StringUtils.isBlank(username)) {
			return ResponseVO.fail(ResponseCode.ERR_418.getCode(), "用户名不能为空");
		}
		if (StringUtils.isBlank(password)) {
			return ResponseVO.fail(ResponseCode.ERR_418.getCode(), "密码不能为空");
		}
		Subject user = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
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

}
