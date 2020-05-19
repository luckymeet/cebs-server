package com.ycw.cebs.info.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.ycw.cebs.info.domain.InfoArticle;
import com.ycw.common.base.BaseCrudMapper;

/**
 * 文章公告通知Mapper接口
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
@Mapper
public interface IInfoArticleMapper extends BaseCrudMapper<InfoArticle> {

}

