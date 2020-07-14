package com.ycw.cebs.info.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ycw.cebs.info.entity.InfoArticle;
import com.ycw.cebs.info.param.InfoArticlePageParam;
import com.ycw.cebs.info.vo.InfoArticleListVO;
import com.ycw.common.base.BaseCrudMapper;

/**
 * 文章Mapper接口
 *
 * @author yuminjun yuminjun@lexiangbao.com
 * @date 2020/05/19 14:39:58
 * @version v1.00
 *
 * @record
 *
 *         <pre>
 * version  author      date          desc
 * -------------------------------------------------
 * 1.00     yuminjun    2020/05/19    新建
 * -------------------------------------------------
 *         </pre>
 */
@Mapper
public interface InfoArticleMapper extends BaseCrudMapper<InfoArticle> {

	/**
	 * 文章列表查询
	 *
	 * @author yuminjun
	 * @date 2020/05/19 16:39:59
	 * @param sysUserListParam
	 * @return
	 */
	List<InfoArticleListVO> queryInfoArticlePage(@Param("params") InfoArticlePageParam infoArticlePageParam);

}
