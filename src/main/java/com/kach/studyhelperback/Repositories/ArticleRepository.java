package com.kach.studyhelperback.Repositories;

import com.kach.studyhelperback.Models.DAO.Article;
import com.kach.studyhelperback.Models.DAO.ArticleType;
import com.kach.studyhelperback.Models.Helpers.ArticleMin;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository extends CrudRepository<Article, Integer> {
    List<Article> findAllByType(ArticleType type);
//    Iterable<Article> findAllByTypeName(String typeName);
    List<ArticleMin> findAllByTypeId(Integer typeId);
//    Optional<ArticleMin> findBy
}
