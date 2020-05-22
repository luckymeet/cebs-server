package com.ycw.cebs.info.api;

import com.github.pagehelper.PageInfo;
import com.ycw.cebs.info.param.InfoArticlePageParam;
import com.ycw.cebs.info.vo.InfoArticleListVO;
import com.ycw.common.page.PageParams;
import com.ycw.common.response.ResponseVO;

/**
 * 文章公告通知Api接口
 * @author yuminjun
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
public interface IInfoArticleApi {

	/**
	 * 文章列表分页查询
	 * @author yuminjun
	 * @date 2020/05/19 16:45:07
	 * @param infoArticlePageParamVO 查询参数
	 * @param pageParams 分页参数（为空时默认查询所有）
	 * @return
	 */
	ResponseVO<PageInfo<InfoArticleListVO>> queryInfoArticlePage(InfoArticlePageParam infoArticlePageParamVO, PageParams pageParams);

}
