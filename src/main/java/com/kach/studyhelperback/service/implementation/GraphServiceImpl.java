package com.kach.studyhelperback.service.implementation;

import com.kach.studyhelperback.model.Article;
import com.kach.studyhelperback.model.ArticlesRelations;
import com.kach.studyhelperback.repository.ArticlesRelationsRepository;
import com.kach.studyhelperback.service.ArticleService;
import com.kach.studyhelperback.service.GraphService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GraphServiceImpl implements GraphService {
//    @Override
//    public void topSort() {
//
//    }
//
//    @Override
//    public List<Article> findPath(Long startId, Long endId) {
//        return null;
//    }

    @Autowired
    ArticlesRelationsRepository articlesRelationsRepository;

    @Autowired
    ArticleService articleService;

    @Override
    public List<Article> nearestArticles(Long articleId) {
        List<Article> nearArticles = new ArrayList<>();
        List<ArticlesRelations> articlesFromThis = articlesRelationsRepository.findAllByFrom_Id(articleId);
        List<ArticlesRelations> articlesToThis = articlesRelationsRepository.findAllByTo_Id(articleId);
        for (ArticlesRelations articlesRelations : articlesToThis){
            nearArticles.add(articleService.getArticle(articlesRelations.getId()));
        }
        for (ArticlesRelations articlesRelations : articlesFromThis){
            nearArticles.add(articleService.getArticle(articlesRelations.getId()));
        }
        return nearArticles;
    }

    @Override
    public void addRelation(Long curId, Long fromId) {
        ArticlesRelations articlesRelation = new ArticlesRelations();
        articlesRelation.setFrom(articleService.getArticle(fromId));
        articlesRelation.setTo(articleService.getArticle(curId));
        articlesRelationsRepository.save(articlesRelation);
    }
}
