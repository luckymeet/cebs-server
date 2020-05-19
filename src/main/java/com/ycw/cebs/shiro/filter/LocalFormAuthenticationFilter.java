package com.ycw.cebs.shiro.filter;

import java.io.PrintWriter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSON;
import com.ycw.common.response.ResponseCode;
import com.ycw.common.response.ResponseVO;

import lombok.extern.slf4j.Slf4j;

/**
 * 重写权限验证问题，登录失效后返回状态码
 */
@Slf4j
public class LocalFormAuthenticationFilter extends FormAuthenticationFilter {

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		if (isLoginRequest(request, response)) {
			if (isLoginSubmission(request, response)) {
				log.info("Login submission detected.  Attempting to execute login.");
				return executeLogin(request, response);
			} else {
				log.info("Login page view.");
				return true;
			}
		} else {
			HttpServletRequest req = (HttpServletRequest) request;
			HttpServletResponse res = (HttpServletResponse) response;
			if (req.getMethod().equals(RequestMethod.OPTIONS.name())) {
				res.setStatus(HttpStatus.OK.value());
				return true;
			}
			res.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
			log.info("Attempting to access a path which requires authentication.  Forwarding to the "
					+ "Authentication url [" + getLoginUrl() + "]");

			PrintWriter out = res.getWriter();
			ResponseVO<String> result = ResponseVO.fail(ResponseCode.ERR_401.getCode(), ResponseCode.ERR_401.getDesc());
			out.println(JSON.toJSONString(result));
			out.flush();
			out.close();
			return false;
		}
	}


}
