package com.ycw.cebs.info.param;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.ycw.common.constants.CommonConstants;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class InfoArticlePublishParam implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 标题
	 */
	@NotNull(message = "id不能为空")
	private Long id;

	/**
	 * 发布时间
	 */
	@NotNull(message = "请选择发布时间")
	@DateTimeFormat(pattern = CommonConstants.DATE_TIME_24HOUR_FORMAT)
	private LocalDateTime publishTime;

}
