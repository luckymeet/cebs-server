package com.ycw.cebs.common.base;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.Version;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import lombok.Data;

@Data
public class BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId(value = "id", type = IdType.AUTO)
	private Long id;

	/**
	 * 删除标识：0-未删除；1-删除
	 */
	@TableLogic
	@TableField(value = "del_ind")
	private Integer delInd;

	/**
	 * 创建人
	 */
	@TableField(value = "create_user", fill = FieldFill.INSERT)
	private Long createUser;

	/**
	 * 创建时间
	 */
	@TableField(value = "create_time", fill = FieldFill.INSERT)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
	private LocalDateTime createTime;

	/**
	 * 更新人
	 */
	@TableField(value = "update_user", fill = FieldFill.INSERT_UPDATE)
	private Long updateUser;

	/**
	 * 更新时间
	 */
	@TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
	private LocalDateTime updateTime;

	/**
	 * 版本号
	 */
	@Version
	@TableField(value = "version")
	private Integer version;

}
