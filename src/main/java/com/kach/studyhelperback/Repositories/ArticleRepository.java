package com.kach.studyhelperback.Repositories;

import com.kach.studyhelperback.Models.DAO.Article;
import com.kach.studyhelperback.Models.DAO.ArticleType;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository<Article, Integer> {
    Iterable<Article> findAllByType(ArticleType type);
}
