package com.ycw.cebs.info.param;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.ycw.common.constants.CommonConstants;

import lombok.Data;

@Data
public class InfoArticlePageParam implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 标题
	 */
	private String title;

	/**
	 * 发布开始时间
	 */
	@DateTimeFormat(pattern = CommonConstants.DATE_TIME_24HOUR_FORMAT)
	private LocalDateTime publishStartTime;

	/**
	 * 发布结束时间
	 */
	@DateTimeFormat(pattern = CommonConstants.DATE_TIME_24HOUR_FORMAT)
	private LocalDateTime publishEndTime;

	/**
	 * 是否推荐：0-否，1-是
	 */
	private Integer isRecommend;

	/**
	 * 状态：1-生效，0-未生效
	 */
	private Integer status;

	/**
	 * 排序
	 */
	private String orderBy;

}
