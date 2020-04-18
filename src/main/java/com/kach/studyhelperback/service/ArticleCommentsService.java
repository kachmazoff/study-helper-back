package com.kach.studyhelperback.service;

import com.kach.studyhelperback.model.Article;
import com.kach.studyhelperback.model.ArticleComments;

import java.util.List;

public interface ArticleCommentsService {
    void addArticleComment(ArticleComments articleComment);
    void addReplyArticleComment(ArticleComments articleComment, ArticleComments replyArticleComment);
    List<ArticleComments> getArticleComment(Long articleId);
    List<ArticleComments> getArticleComment(Article article);
}
