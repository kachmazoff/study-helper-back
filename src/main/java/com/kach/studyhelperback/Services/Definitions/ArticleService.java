package com.kach.studyhelperback.Services.Definitions;

import com.kach.studyhelperback.Models.DAO.Article;
import com.kach.studyhelperback.Models.DAO.ArticleType;

import java.util.Optional;

public interface ArticleService {
    Optional<Article> getArticle(Integer id);
    Iterable<Article> getAllArticles();
    Iterable<Article> getAllArticles(ArticleType type);
    Integer createArticle(String header, String articleType, String text);
    void setArticlePublished(Boolean isPublished);
    void updateArticleText(Integer articleId, String text);
    void updateArticleHeader(Integer articleId, String header);
    void updateArticleType(Integer articleId, String typeName);

    Optional<ArticleType> getArticleType(Integer id);
    Optional<ArticleType> getArticleType(String name);
    Iterable<ArticleType> getArticleTypes();
    Integer createArticleType(String name);
    void deleteArticleType(String name);
    void renameArticleType(String oldName, String newName);
}
