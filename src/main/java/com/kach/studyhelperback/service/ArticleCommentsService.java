package com.kach.studyhelperback.service;

import com.kach.studyhelperback.model.Article;
import com.kach.studyhelperback.model.ArticleComments;

import java.util.List;
import java.util.Optional;

public interface ArticleCommentsService {
    /**
     * Добавление комментария к статье
     * @param articleComment Объект комментария ArticleComment
     */
    void addArticleComment(ArticleComments articleComment);

    /**
     * Добавление ответа к существующему комментарию
     * @param articleCommentId ID существующего комментария
     * @param replyArticleComment Объект ответа на комментарий
     */
    void addReplyArticleComment(Long articleCommentId, ArticleComments replyArticleComment);

    /**
     * Получение комментариев к статье
     * @param articleId ID статьи
     * @return Список комментариев
     */
    List<ArticleComments> getArticleComments(Long articleId);

    /**
     * Получение комментариев к статье
     * @param article Объект статьи
     * @return Список комментариев
     */
    List<ArticleComments> getArticleComments(Article article);

    /**
     * Получение одного комментария
     * @param articleCommentId ID искомого комментария комментария
     * @return Объект комментария
     */
    Optional<ArticleComments> getArticleComment(Long articleCommentId);
}
