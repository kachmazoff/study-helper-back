package com.kach.studyhelperback.service;

import com.kach.studyhelperback.model.Article;
import com.kach.studyhelperback.model.ArticleComments;

import java.util.List;
import java.util.Optional;

public interface ArticleCommentsService {
    void addArticleComment(ArticleComments articleComment);
//    void addReplyArticleComment(ArticleComments articleComment, ArticleComments replyArticleComment);
    void addReplyArticleComment(Long ArticleId, ArticleComments replyArticleComment);
    List<ArticleComments> getArticleComments(Long articleId);
    List<ArticleComments> getArticleComments(Article article);
    Optional<ArticleComments> getArticleComment(Long articleCommentId);
}
