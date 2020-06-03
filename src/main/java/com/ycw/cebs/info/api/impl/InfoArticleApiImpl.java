package com.ycw.cebs.info.api.impl;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.pagehelper.PageInfo;
import com.ycw.cebs.info.api.InfoArticleApi;
import com.ycw.cebs.info.entity.InfoArticle;
import com.ycw.cebs.info.param.InfoArticleAddParam;
import com.ycw.cebs.info.param.InfoArticleEditParam;
import com.ycw.cebs.info.param.InfoArticlePageParam;
import com.ycw.cebs.info.service.InfoArticleService;
import com.ycw.cebs.info.vo.InfoArticleListVO;
import com.ycw.common.constants.CommonConstants;
import com.ycw.common.exception.SysException;
import com.ycw.common.page.PageParam;
import com.ycw.common.response.ResponseCode;
import com.ycw.common.response.ResponseVO;
import com.ycw.common.utils.BeanHandleUtils;

/**
 * 文章Api接口实现类
 * @author yuminjun
 * @date 2020/05/19 14:39:58
 * @version v1.00

 * @record
 * <pre>
 * version  author      date          desc
 * -------------------------------------------------
 * 1.00     yuminjun    2020/05/19    新建
 * -------------------------------------------------
 * </pre>
 */
@Service
public class InfoArticleApiImpl implements InfoArticleApi {

	@Autowired
	private InfoArticleService infoArticleService;

	/**
	 * 文章列表分页查询
	 * @author yuminjun
	 * @date 2020/05/19 16:45:07
	 * @param infoArticlePageParam 查询参数
	 * @param pageParam 分页参数（为空时默认查询所有）
	 * @return
	 */
	@Override
	public ResponseVO<PageInfo<InfoArticleListVO>> queryInfoArticlePage(InfoArticlePageParam infoArticlePageParam,
			PageParam pageParam) {
		List<InfoArticleListVO> page = infoArticleService.queryInfoArticlePage(infoArticlePageParam, pageParam);
		return ResponseVO.success(new PageInfo<>(page));
	}

	/**
	 * 文章详情
	 * @author yuminjun
	 * @date 2020/05/25 16:26:36
	 * @param id
	 * @return
	 */
	@Override
	public ResponseVO<InfoArticle> getInfoArticle(Long id) {
		InfoArticle infoArticle = this.infoArticleService.getById(id);
		return ResponseVO.success(infoArticle);
	}

	/**
	 * 文章新增
	 * @author yuminjun
	 * @date 2020/05/25 11:01:04
	 * @param infoArticleAddParam
	 * @return
	 */
	@Override
	public ResponseVO<Long> saveInfoArticle(InfoArticleAddParam infoArticleAddParam) {
		LocalDateTime publishTime = infoArticleAddParam.getPublishTime();
		validPublishTime(publishTime);
		InfoArticle infoArticle = BeanHandleUtils.beanCopy(infoArticleAddParam, InfoArticle.class);
		this.infoArticleService.save(infoArticle);
		return ResponseVO.success(infoArticle.getId(), "新增成功");
	}

	private void validPublishTime(LocalDateTime publishTime) {
		if (null == publishTime) {
			return;
		}
		LambdaQueryWrapper<InfoArticle> queryWrapper = Wrappers.lambdaQuery();
		queryWrapper.ge(InfoArticle::getPublishTime, LocalDateTime.of(publishTime.toLocalDate(), LocalTime.MIN));
		queryWrapper.le(InfoArticle::getPublishTime, LocalDateTime.of(publishTime.toLocalDate(), LocalTime.MAX));
		queryWrapper.eq(InfoArticle::getStatus, CommonConstants.INT_YES);
		if (this.infoArticleService.count(queryWrapper) > 0) {
			throw new SysException(ResponseCode.ERR_419.getCode(), "该发布时间已被其它文章占用，请重新选择发布时间");
		}
	}

	/**
	 * 文章修改
	 * @author yuminjun
	 * @date 2020/05/25 11:01:04
	 * @param infoArticleEditParam
	 * @return
	 */
	@Override
	public ResponseVO<String> updateInfoArticle(InfoArticleEditParam infoArticleEditParam) {
		InfoArticle infoArticle = this.infoArticleService.getById(infoArticleEditParam.getId());
		LocalDateTime publishTime = infoArticleEditParam.getPublishTime();
		if (null != publishTime && (null == infoArticle.getPublishTime() || !publishTime.toLocalDate().equals(infoArticle.getPublishTime().toLocalDate()))) {
			validPublishTime(publishTime);
		}
		BeanHandleUtils.copyProperties(infoArticleEditParam, infoArticle);
		this.infoArticleService.updateById(infoArticle);
		return ResponseVO.success(null, "修改成功");
	}

	/**
	 * 文章删除
	 * @author yuminjun
	 * @date 2020/05/25 14:39:24
	 * @param id
	 * @return
	 */
	@Override
	public ResponseVO<String> deleteInfoArticle(Long id) {
		this.infoArticleService.removeById(id);
		return ResponseVO.success(null, "删除成功");
	}

	/**
	 * 发布
	 * @author yuminjun
	 * @date 2020/05/25 15:26:16
	 * @param id 文章id
	 * @param publishTime 发布时间
	 * @return
	 */
	@Override
	public ResponseVO<String> publish(Long id, LocalDateTime publishTime) {
		this.validPublishTime(publishTime);
		this.infoArticleService.lambdaUpdate()
			.set(InfoArticle::getPublishTime, publishTime)
			.eq(InfoArticle::getId, id)
			.update();
		return ResponseVO.success(null, "发布成功");
	}

	/**
	 * 取消发布
	 * @author yuminjun
	 * @date 2020/05/25 15:10:25
	 * @param id
	 * @return
	 */
	@Override
	public ResponseVO<String> cancelPublish(Long id) {
		InfoArticle infoArticle = this.infoArticleService.getById(id);
		LocalDateTime publishTime = infoArticle.getPublishTime();
		if (null != publishTime && publishTime.isBefore(LocalDateTime.now())) {
			throw new SysException(ResponseCode.ERR_420.getCode(), "该文章已到达发布时间，不可取消发布");
		}
		this.infoArticleService.lambdaUpdate()
			.set(InfoArticle::getPublishTime, null)
			.eq(InfoArticle::getId, id)
			.update();
		return ResponseVO.success(null, "取消成功");
	}

	/**
	 * 文章状态修改
	 * @author yuminjun
	 * @date 2020/05/25 15:44:02
	 * @param id
	 * @param status
	 * @return
	 */
	@Override
	public ResponseVO<String> changeStatus(Integer id, Integer status) {
		this.infoArticleService.lambdaUpdate()
			.set(InfoArticle::getStatus, status)
			.eq(InfoArticle::getId, id)
			.update();
		return ResponseVO.success(null, "修改成功");
	}

	/**
	 * 文章推荐状态修改
	 * @author yuminjun
	 * @date 2020/05/25 15:44:02
	 * @param id
	 * @param isRecommend
	 * @return
	 */
	@Override
	public ResponseVO<String> changeRecommend(Integer id, Integer isRecommend) {
		this.infoArticleService.lambdaUpdate()
			.set(InfoArticle::getIsRecommend, isRecommend)
			.eq(InfoArticle::getId, id)
			.update();
		return ResponseVO.success(null, "修改成功");
	}

}

