package com.ycw.cebs.common.vo;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class TreeVO implements Serializable {

	private static final long serialVersionUID = 1L;

    private Long parentId;

	private Long id;

	private String label;

	private Integer order;

	private List<TreeVO> children;

}
