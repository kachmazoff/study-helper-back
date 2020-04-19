package com.kach.studyhelperback.service;

import java.util.Date;
import java.util.HashMap;

public interface ArticleStatService {
    Long getArticleStat (Long articleId);
//    Long getArticlesStat();
    Long getArticlesStatByDate (Date date);
    HashMap<String, Long> getArticlesStat();
}
