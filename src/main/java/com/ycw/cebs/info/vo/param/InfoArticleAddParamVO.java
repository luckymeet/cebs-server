package com.ycw.cebs.info.vo.param;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.ycw.common.constants.CommonConstants;

import lombok.Data;

@Data
public class InfoArticleAddParamVO implements Serializable {

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
 	 * 状态：1-生效，0-未生效
 	 */
	private Integer status;

 	/**
 	 * 发布时间
 	 */
	@DateTimeFormat(pattern = CommonConstants.DATE_TIME_24HOUR_FORMAT)
	private LocalDateTime publishTime;

 	/**
 	 * 是否展示免责申明：1-展示，0-不展示
 	 */
	private Integer isShowDisclaimer;

}
