package com.ycw.cebs.info.domain;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ycw.cebs.common.base.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 文章公告通知实体类
 * @author yuminjun yuminjun@lexiangbao.com
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
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("info_article")
public class InfoArticle extends BaseEntity {

	private static final long serialVersionUID = 1L;

 	/**
 	 * 标题
 	 */
	@TableField("title")
	private String title;

 	/**
 	 * 类型
 	 */
	@TableField("type")
	private Integer type;

 	/**
 	 * 作者
 	 */
	@TableField("author")
	private String author;

 	/**
 	 * 源作者
 	 */
	@TableField("source_author")
	private String sourceAuthor;

 	/**
 	 * 原文链接
 	 */
	@TableField("source_link")
	private String sourceLink;

 	/**
 	 * 图片id
 	 */
	@TableField("image_id")
	private Long imageId;

 	/**
 	 * 简介
 	 */
	@TableField("introduction")
	private String introduction;

 	/**
 	 * 内容
 	 */
	@TableField("content")
	private String content;

 	/**
 	 * 状态：1-生效，0-未生效
 	 */
	@TableField("status")
	private Integer status;

 	/**
 	 * 发布时间
 	 */
	@TableField("publish_time")
	private LocalDateTime publishTime;

 	/**
 	 * 是否展示免责申明：1-展示，0-不展示
 	 */
	@TableField("is_show_disclaimer")
	private Integer isShowDisclaimer;

}
