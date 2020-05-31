package com.ycw.cebs.info.controller;

import java.time.LocalDateTime;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.ycw.cebs.info.api.IInfoArticleApi;
import com.ycw.cebs.info.entity.InfoArticle;
import com.ycw.cebs.info.param.InfoArticleAddParam;
import com.ycw.cebs.info.param.InfoArticleEditParam;
import com.ycw.cebs.info.param.InfoArticlePageParam;
import com.ycw.cebs.info.vo.InfoArticleListVO;
import com.ycw.common.page.PageParam;
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
	 * @param infoArticlePageParam 查询参数
	 * @param pageParam 分页参数（为空时默认查询所有）
	 * @return
	 */
	@GetMapping("/list")
	@RequiresPermissions("info:article:list")
	public ResponseVO<PageInfo<InfoArticleListVO>> queryInfoArticlePage(InfoArticlePageParam infoArticlePageParam,
			PageParam pageParam) {
		return infoArticleApi.queryInfoArticlePage(infoArticlePageParam, pageParam);
	}

	/**
	 * 文章详情
	 * @author yuminjun
	 * @date 2020/05/25 16:26:36
	 * @param id
	 * @return
	 */
	@GetMapping
	@RequiresPermissions("info:article:edit")
	public ResponseVO<InfoArticle> getInfoArticleForUpdate(Long id) {
		return infoArticleApi.getInfoArticle(id);
	}

	/**
	 * 文章详情
	 * @author yuminjun
	 * @date 2020/05/25 16:26:36
	 * @param id
	 * @return
	 */
	@GetMapping("/view")
	@RequiresPermissions("info:article:view")
	public ResponseVO<InfoArticle> getInfoArticleForView(Long id) {
		return infoArticleApi.getInfoArticle(id);
	}

	/**
	 * 文章新增
	 * @author yuminjun
	 * @date 2020/05/25 11:01:04
	 * @param infoArticleAddParam
	 * @return
	 */
	@PostMapping
	@RequiresPermissions("info:article:add")
	public ResponseVO<Long> saveInfoArticle(@RequestBody InfoArticleAddParam infoArticleAddParam) {
		return infoArticleApi.saveInfoArticle(infoArticleAddParam);
	}

	/**
	 * 文章修改
	 * @author yuminjun
	 * @date 2020/05/25 11:01:04
	 * @param infoArticleEditParam
	 * @return
	 */
	@PutMapping
	@RequiresPermissions("info:article:edit")
	public ResponseVO<String> updateInfoArticle(@RequestBody InfoArticleEditParam infoArticleEidtParam) {
		return infoArticleApi.updateInfoArticle(infoArticleEidtParam);
	}

	/**
	 * 文章删除
	 * @author yuminjun
	 * @date 2020/05/25 14:39:24
	 * @param id
	 * @return
	 */
	@DeleteMapping
	@RequiresPermissions("info:article:delete")
	public ResponseVO<String> deleteInfoArticle(Long id) {
		return infoArticleApi.deleteInfoArticle(id);
	}

	/**
	 * 发布
	 * @author yuminjun
	 * @date 2020/05/25 15:26:16
	 * @param id 文章id
	 * @param publishTime 发布时间
	 * @return
	 */
	@PutMapping("/publish")
	@RequiresPermissions("info:article:publish")
	public ResponseVO<String> publish(Long id, LocalDateTime publishTime) {
		return infoArticleApi.publish(id, publishTime);
	}

	/**
	 * 取消发布
	 * @author yuminjun
	 * @date 2020/05/25 15:21:05
	 * @param id
	 * @return
	 */
	@PutMapping("/cancel-publish")
	@RequiresPermissions("info:article:cancel-publish")
	public ResponseVO<String> cancelPublish(Long id) {
		return infoArticleApi.cancelPublish(id);
	}

	/**
	 * 文章状态修改
	 * @author yuminjun
	 * @date 2020/05/25 15:44:02
	 * @param id
	 * @param status
	 * @return
	 */
	@PutMapping("/status/change")
	@RequiresPermissions("sys:user:status:change")
	public ResponseVO<String> changeStatus(Integer id, Integer status) {
		return infoArticleApi.changeStatus(id, status);
	}

	/**
	 * 文章推荐状态修改
	 * @author yuminjun
	 * @date 2020/05/25 15:44:02
	 * @param id
	 * @param isRecommend
	 * @return
	 */
	@PutMapping("/recommend/change")
	@RequiresPermissions("sys:user:recommend:change")
	public ResponseVO<String> changeRecommend(Integer id, Integer isRecommend) {
		return infoArticleApi.changeRecommend(id, isRecommend);
	}

}
