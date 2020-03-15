package com.kach.studyhelperback.Services.Definitions;

import com.kach.studyhelperback.Models.DAO.Article;
import com.kach.studyhelperback.Models.DAO.ArticleType;

import java.util.List;
import java.util.Optional;

public interface ArticleService {
    Article getArticle(Integer id);
    List<Article> getAllArticles();
    List<Article> getAllArticles(ArticleType type);
    void addArticle(Article article);
    void updateArticle(Integer articleId, Article article);
    void deleteArticle(Integer articleId);

    void setArticlePublished(Integer articleId, Boolean isPublished);
}
