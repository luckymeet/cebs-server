package com.ycw.cebs.info.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ycw.cebs.info.domain.InfoArticle;
import com.ycw.cebs.info.vo.InfoArticleListVO;
import com.ycw.cebs.info.vo.param.InfoArticlePageParamVO;
import com.ycw.common.page.PageParams;

/**
 * 文章公告通知Service接口
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
public interface IInfoArticleService extends IService<InfoArticle> {

	/**
	 * 文章列表分页查询
	 * @author yuminjun
	 * @date 2020/05/19 16:45:07
	 * @param infoArticlePageParamVO 查询参数
	 * @param pageParams 分页参数（为空时默认查询所有）
	 * @return
	 */
	List<InfoArticleListVO> queryInfoArticlePage(InfoArticlePageParamVO infoArticlePageParamVO, PageParams pageParams);

}
