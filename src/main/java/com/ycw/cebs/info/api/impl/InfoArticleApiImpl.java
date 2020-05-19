package com.ycw.cebs.info.api.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ycw.cebs.info.domain.InfoArticle;
import com.ycw.cebs.info.mapper.IInfoArticleMapper;
import com.ycw.cebs.info.service.IInfoArticleService;

/**
 * 文章公告通知Service接口实现类
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
@Service
public class InfoArticleApiImpl extends ServiceImpl<IInfoArticleMapper, InfoArticle> implements IInfoArticleService {

	@Autowired
	private IInfoArticleMapper infoArticleMapper;

}

