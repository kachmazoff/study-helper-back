package com.kach.studyhelperback.Repositories;

import com.kach.studyhelperback.Models.DAO.Article;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository<Article, Integer> {
}
