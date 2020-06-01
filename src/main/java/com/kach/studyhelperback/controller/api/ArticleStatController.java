package com.kach.studyhelperback.controller.api;

import com.kach.studyhelperback.service.ArticleStatService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.HashMap;

public class ArticleStatController {
    // TODO: hmm..
    @Autowired
    ArticleStatService articleStatService;

    public Long getArticleStat(Long articleId){
        return articleStatService.getArticleViews(articleId);
    }

    public Long getArticlesStatByDate(Date date){
        return articleStatService.getArticlesStatByDate(date);
    }

    public HashMap<String, Long> getArticlesStat() {
        return articleStatService.getArticlesStat();
    }
}
