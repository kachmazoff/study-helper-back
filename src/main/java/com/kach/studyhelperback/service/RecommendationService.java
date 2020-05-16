package com.kach.studyhelperback.service;

import com.kach.studyhelperback.model.Article;
import com.kach.studyhelperback.model.User;

import java.util.List;

public interface RecommendationService {
    List<Article> get();
    List<Article> get(User user);
    List<Article> get(User user, Article article);
    List<Article> get(User user, Long articleId);
    List<Article> getTopArticles();
    List<Article> getHotArticles();
}
