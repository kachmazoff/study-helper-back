package com.kach.studyhelperback.service;

import com.kach.studyhelperback.model.Article;
import com.kach.studyhelperback.model.ArticleType;

import java.util.List;

public interface ArticleService {
    Article getArticle(Long id);
    Article getFullArticle(Long id);
    List<Article> getNewArticles();
    List<Article> getAllArticles();
    List<Article> getAllArticles(ArticleType type);
    List<Article> getAllMyArticles();
    List<Article> getLastViewedArticles();
    void addArticle(Article article);
    void updateArticle(Long articleId, Article article);
    void deleteArticle(Long articleId);

    void setArticlePublished(Long articleId, Boolean isPublished);
}
