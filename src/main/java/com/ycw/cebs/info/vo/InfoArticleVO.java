package com.ycw.cebs.info.vo;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ycw.common.constants.CommonConstants;

import lombok.Data;

@Data
public class InfoArticleVO implements Serializable {

	private static final long serialVersionUID = 1L;

 	/**
 	 * id
 	 */
	private Long id;

 	/**
 	 * 标题
 	 */
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
	private String content;

	/**
	 * 是否推荐：0-否，1-是
	 */
	private Integer isRecommend;

 	/**
 	 * 状态：1-生效，0-未生效
 	 */
	private Integer status;

 	/**
 	 * 发布时间
 	 */
	@JsonFormat(pattern = CommonConstants.DATE_TIME_24HOUR_FORMAT, timezone = "GMT+8")
	private LocalDateTime publishTime;

 	/**
 	 * 是否展示免责申明：1-展示，0-不展示
 	 */
	private Integer isShowDisclaimer;

}
