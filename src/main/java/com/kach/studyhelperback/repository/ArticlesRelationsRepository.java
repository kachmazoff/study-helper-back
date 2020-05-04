package com.kach.studyhelperback.repository;

import com.kach.studyhelperback.model.Article;
import com.kach.studyhelperback.model.ArticlesRelations;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticlesRelationsRepository extends JpaRepository<ArticlesRelations, Long> {
    List<ArticlesRelations> findAllByFrom(Article from);
    List<ArticlesRelations> findAllByFrom_Id(Long fromId);

    List<ArticlesRelations> findAllByTo(Article to);
    List<ArticlesRelations> findAllByTo_Id(Long toId);
}
