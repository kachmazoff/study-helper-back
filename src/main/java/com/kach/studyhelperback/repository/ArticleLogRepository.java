package com.kach.studyhelperback.repository;

import com.kach.studyhelperback.model.Article;
import com.kach.studyhelperback.model.ArticleLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface ArticleLogRepository extends JpaRepository<ArticleLog, Long> {
    List<ArticleLog> findAllByArticle(Article article);
    List<ArticleLog> findAllByArticle_Id(Long articleId);
    List<ArticleLog> findAllByCreated(Date date);
//    List<ArticleLog> findAllByArticle_Creator_IdAndArticle_Id(Long articleCreatorId, Long articleId);
    List<ArticleLog> findAllByArticle_Creator_Id(Long articleCreatorId);
    List<ArticleLog> findAllByArticle_Creator_IdAndArticle_Created(Long article_creator_id, Date article_created);
    List<ArticleLog> findAllByUser_Id(Long userId);
}
