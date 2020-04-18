package com.kach.studyhelperback.repository;

import com.kach.studyhelperback.model.Article;
import com.kach.studyhelperback.model.ArticleComments;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ArticleCommentsRepository extends CrudRepository<ArticleComments, Long> {
    List<ArticleComments> findAllByArticleId(Long article_id);
    List<ArticleComments> findAllByArticle(Article article);
}