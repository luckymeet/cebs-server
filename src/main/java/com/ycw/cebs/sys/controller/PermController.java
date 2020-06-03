package com.ycw.cebs.sys.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ycw.cebs.common.constant.sys.PermTypeEnum;
import com.ycw.cebs.common.vo.TreeVO;
import com.ycw.cebs.sys.api.SysPermApi;
import com.ycw.common.response.ResponseVO;

@RestController
@RequestMapping("/sys/perm")
public class PermController {

	@Autowired
	private SysPermApi sysPermApi;

	/**
	 * 查询当前用户权限路由权限
	 *
	 * @author yuminjun
	 * @date 2020/04/23 15:34:29
	 * @return
	 */
	@GetMapping("/menu")
	public ResponseVO<List<String>> queryPermPerm() {
		ResponseVO<List<String>> routeList = sysPermApi.queryPermList(PermTypeEnum.MENU.getCode());
		return routeList;
	}

	/**
	 * 查询当前用户按钮路由权限
	 *
	 * @author yuminjun
	 * @date 2020/04/23 15:34:44
	 * @return
	 */
	@GetMapping("/button")
	public ResponseVO<List<String>> queryButtonPerm() {
		ResponseVO<List<String>> routeList = sysPermApi.queryPermList(PermTypeEnum.BUTTON.getCode());
		return routeList;
	}

	/**
	 * 获取当前用户权限树
	 *
	 * @author yuminjun
	 * @date 2020/05/15 14:16:48
	 * @return
	 */
	@GetMapping("/tree")
	public ResponseVO<List<TreeVO>> queryCurUserPermTree() {
		return sysPermApi.queryCurUserPermTree();
	}

}
