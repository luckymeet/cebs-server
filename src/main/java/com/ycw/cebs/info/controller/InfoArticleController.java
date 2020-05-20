package com.ycw.cebs.info.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.ycw.cebs.info.api.IInfoArticleApi;
import com.ycw.cebs.info.vo.InfoArticleListVO;
import com.ycw.cebs.info.vo.param.InfoArticlePageParamVO;
import com.ycw.common.page.PageParams;
import com.ycw.common.response.ResponseVO;

@RestController
@RequestMapping("/info/article")
public class InfoArticleController {

	@Autowired
	private IInfoArticleApi infoArticleApi;

	/**
	 * 文章列表分页查询
	 * @author yuminjun
	 * @date 2020/05/19 16:45:07
	 * @param infoArticlePageParamVO 查询参数
	 * @param pageParams 分页参数（为空时默认查询所有）
	 * @return
	 */
	@GetMapping("/list")
	@RequiresPermissions("info:article:list")
	public ResponseVO<PageInfo<InfoArticleListVO>> queryInfoArticlePage(InfoArticlePageParamVO infoArticlePageParamVO,
			PageParams pageParams) {
		return infoArticleApi.queryInfoArticlePage(infoArticlePageParamVO, pageParams);
	}

}
