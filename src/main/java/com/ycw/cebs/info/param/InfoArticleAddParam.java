package com.ycw.cebs.info.param;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
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
	@Length(max = 50, message = "标题长度不能超过50个字符")
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
	@Length(max = 500, message = "源链接不能超过500个字符")
	private String sourceLink;

	/**
	 * 图片id
	 */
	private Long imageId;

	/**
	 * 简介
	 */
	@Length(max = 200, message = "简介长度不能超过200个字符")
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
	@DateTimeFormat(pattern = CommonConstants.DATE_TIME_24HOUR_FORMAT)
	private LocalDateTime publishTime;

	/**
	 * 是否展示免责申明：1-展示，0-不展示
	 */
	private Integer isShowDisclaimer;

}
