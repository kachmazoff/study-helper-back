package com.kach.studyhelperback.service;

import com.kach.studyhelperback.model.Article;
import com.kach.studyhelperback.model.ArticlesRelations;

import java.util.List;

public interface ArticlesRelationsService {
    void addRelation(Long from, Long to, Double weight);
    void addRelation(Article from, Article to, Double weight);

    List<ArticlesRelations> getRelations(Article article, Integer depth);
    List<ArticlesRelations> getRelations(Long articleId, Integer depth);
}
