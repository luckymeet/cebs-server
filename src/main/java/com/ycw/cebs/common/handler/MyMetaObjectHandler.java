 package com.ycw.cebs.common.handler;

import java.time.LocalDateTime;
import java.util.Optional;

import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.ycw.cebs.common.utils.SessionUtil;

/**
 * 新增/修改数据时，公共字段填充
 *
 * @author yuminjun
 * @date 2020/04/30 11:43:33
 * @version 1.00
 *
 * @record
 * <pre>
 * version  author      date          desc
 * -------------------------------------------------
 * 1.00     yuminjun    2020/04/30    新建
 * -------------------------------------------------
 * </pre>
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

	private static final String CREATE_USER = "createUser";

	private static final String UPDATE_USER = "updateUser";

	private static final String CREATE_TIME = "createTime";

	private static final String UPDATE_TIME = "updateTime";

	@Override
	public void insertFill(MetaObject metaObject) {
		LocalDateTime now = LocalDateTime.now();
		Long currentUserId = Optional.ofNullable(SessionUtil.getCurrentUserId()).orElse(0L);
		this.strictInsertFill(metaObject, CREATE_USER, Long.class, currentUserId);
		this.strictInsertFill(metaObject, UPDATE_USER, Long.class, currentUserId);
		this.strictInsertFill(metaObject, CREATE_TIME, LocalDateTime.class, now);
		this.strictInsertFill(metaObject, UPDATE_TIME, LocalDateTime.class, now);
	}

	@Override
	public void updateFill(MetaObject metaObject) {
		this.strictUpdateFill(metaObject, UPDATE_USER, Long.class, Optional.ofNullable(SessionUtil.getCurrentUserId()).orElse(0L));
		this.strictUpdateFill(metaObject, UPDATE_TIME, LocalDateTime.class, LocalDateTime.now());
	}

}
