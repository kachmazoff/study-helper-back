package com.kach.studyhelperback.service;

import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;

@Service
public interface ArticleStatService {
    Long getArticleStat (Long articleId);
//    Long getArticlesStat();
    Long getArticlesStatByDate (Date date);
    HashMap<String, Long> getArticlesStat();
}
