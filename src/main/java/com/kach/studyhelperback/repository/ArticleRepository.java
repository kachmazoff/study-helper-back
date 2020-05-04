package com.kach.studyhelperback.repository;

import com.kach.studyhelperback.model.Article;
import com.kach.studyhelperback.model.ArticleType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

//import com.kach.studyhelperback.dto.Helpers.ArticleMin;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article> findAllByType(ArticleType type);
    List<Article> findAllByCreator_Id(Long id);
//    Iterable<Article> findAllByTypeName(String typeName);
    List<Article> findAllByTypeId(Integer typeId);
    List<Article> findAllByTitleContains(String query);
    List<Article> findAllByCreatedGreaterThan(Date date);
//    Optional<ArticleMin> findBy
}
