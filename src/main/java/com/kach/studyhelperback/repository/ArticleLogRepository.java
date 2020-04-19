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
}
