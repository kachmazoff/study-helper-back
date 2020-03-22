package com.kach.studyhelperback.repository;

import com.kach.studyhelperback.model.Article;
import com.kach.studyhelperback.model.ArticleType;
//import com.kach.studyhelperback.dto.Helpers.ArticleMin;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ArticleRepository extends CrudRepository<Article, Long> {
    List<Article> findAllByType(ArticleType type);
//    Iterable<Article> findAllByTypeName(String typeName);
    List<Article> findAllByTypeId(Integer typeId);
//    Optional<ArticleMin> findBy
}
