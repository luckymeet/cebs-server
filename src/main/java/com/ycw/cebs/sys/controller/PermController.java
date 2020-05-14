package com.ycw.cebs.sys.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ycw.cebs.common.vo.TreeVO;
import com.ycw.cebs.sys.api.ISysPermApi;
import com.ycw.cebs.sys.vo.SysPermListVO;
import com.ycw.common.response.ResponseVO;

@RestController
@RequestMapping("/sys/perm")
public class PermController {

	@Autowired
	private ISysPermApi sysPermApi;

	/**
	 * 查询当前用户一级权限列表
	 * @author yuminjun
	 * @date 2020/04/30 14:43:42
	 * @return
	 */
	@GetMapping("/one-level/list")
	public ResponseVO<List<SysPermListVO>> queryCurUserOneLevelPermList() {
		return sysPermApi.queryCurUserOneLevelPermList();
	}

	/**
	 * 根据上级权限id查询下级权限列表
	 * @author yuminjun
	 * @date 2020/04/30 16:41:52
	 * @param parentId 上级权限id
	 * @return
	 */
	@GetMapping("/lower-level/list")
	public ResponseVO<List<SysPermListVO>> queryLowerLevelPermList(Long parentId) {
		return sysPermApi.queryPermListByParentId(parentId);
	}

	@GetMapping("/tree")
	public ResponseVO<List<TreeVO>> queryCurUserPermTree() {
		return sysPermApi.queryCurUserPermTree();
	}

}
