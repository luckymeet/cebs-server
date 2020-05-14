package com.ycw.cebs.sys.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ycw.cebs.common.vo.TreeVO;
import com.ycw.cebs.sys.entity.SysPermEntity;
import com.ycw.common.base.BaseCrudMapper;

/**
 * 系统权限Mapper接口
 * @author yuminjun yuminjun@lexiangbao.com
 * @date 2020/04/22 09:30:50
 * @version v1.00

 * @record
 * <pre>
 * version  author      date          desc
 * -------------------------------------------------
 * 1.00     yuminjun    2020/04/22    新建
 * -------------------------------------------------
 * </pre>
 */
@Mapper
public interface ISysPermMapper extends BaseCrudMapper<SysPermEntity> {

	/**
	 * 查询用户权限列表
	 * @author yuminjun
	 * @date 2020/05/13 09:53:19
	 * @param userId 用户id
	 * @return
	 */
	List<TreeVO> queryPermTreeListByUserId(@Param("userId") Long userId);

}

