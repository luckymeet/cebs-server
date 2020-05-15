package com.ycw.cebs.sys.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ycw.cebs.common.vo.TreeVO;
import com.ycw.cebs.sys.api.ISysPermApi;
import com.ycw.common.response.ResponseVO;

@RestController
@RequestMapping("/sys/perm")
public class PermController {

	@Autowired
	private ISysPermApi sysPermApi;

	/**
	 * 获取当前用户权限树
	 * @author yuminjun
	 * @date 2020/05/15 14:16:48
	 * @return
	 */
	@GetMapping("/tree")
	public ResponseVO<List<TreeVO>> queryCurUserPermTree() {
		return sysPermApi.queryCurUserPermTree();
	}

}
