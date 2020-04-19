package com.kach.studyhelperback.service;

import com.kach.studyhelperback.model.Article;

import java.util.List;

public interface GraphService {
//    void topSort();
//    List<Article> findPath(Long startId, Long endId);
    List<Article> nearestArticles(Long articleId);
}
