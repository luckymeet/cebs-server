package com.ycw.cebs.info.param;

import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class InfoArticleEditParam extends InfoArticleAddParam {

	private static final long serialVersionUID = 1L;

	/**
	 * 标题
	 */
	@NotNull(message = "id不能为空")
	private Long id;

}
