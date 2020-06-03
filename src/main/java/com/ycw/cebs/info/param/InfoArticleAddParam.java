package com.ycw.cebs.info.param;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.ycw.common.constants.CommonConstants;

import lombok.Data;

@Data
public class InfoArticleAddParam implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 标题
	 */
	@NotBlank(message = "请输入标题")
	private String title;

	/**
	 * 类型
	 */
	private Integer type;

	/**
	 * 作者
	 */
	private String author;

	/**
	 * 源作者
	 */
	private String sourceAuthor;

	/**
	 * 原文链接
	 */
	private String sourceLink;

	/**
	 * 图片id
	 */
	private Long imageId;

	/**
	 * 简介
	 */
	private String introduction;

	/**
	 * 内容
	 */
	@NotBlank(message = "请输入内容")
	private String content;

	/**
	 * 是否推荐：0-否，1-是
	 */
	@NotNull(message = "请选择是否推荐")
	private Integer isRecommend;

	/**
	 * 状态：1-生效，0-未生效
	 */
	@NotNull(message = "请选择生效状态")
	private Integer status;

	/**
	 * 发布时间
	 */
	@DateTimeFormat(pattern = CommonConstants.DATE_TIME_24HOUR_FORMAT)
	private LocalDateTime publishTime;

	/**
	 * 是否展示免责申明：1-展示，0-不展示
	 */
	@NotNull(message = "请选择是否展示免责声明")
	private Integer isShowDisclaimer;

}
