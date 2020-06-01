package com.kach.studyhelperback.repository;

import com.kach.studyhelperback.model.Article;
import com.kach.studyhelperback.model.ArticleType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article> findAllByType(ArticleType type);
    List<Article> findAllByCreator_Id(Long id);
    List<Article> findAllByTypeId(Integer typeId);
    List<Article> findAllByTitleContains(String query);
    List<Article> findAllByCreatedGreaterThan(Date date);
}
