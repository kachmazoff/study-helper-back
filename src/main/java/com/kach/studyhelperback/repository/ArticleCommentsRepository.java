package com.kach.studyhelperback.repository;

import com.kach.studyhelperback.model.Article;
import com.kach.studyhelperback.model.ArticleComments;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ArticleCommentsRepository extends CrudRepository<ArticleComments, Long> {
    List<ArticleComments> findAllByArticle_Id(Long article_id);
    List<ArticleComments> findAllByArticle(Article article);
}