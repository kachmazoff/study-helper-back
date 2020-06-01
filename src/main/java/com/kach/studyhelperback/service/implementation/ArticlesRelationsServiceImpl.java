package com.kach.studyhelperback.service.implementation;

import com.kach.studyhelperback.model.Article;
import com.kach.studyhelperback.model.ArticlesRelations;
import com.kach.studyhelperback.repository.ArticleRepository;
import com.kach.studyhelperback.repository.ArticlesRelationsRepository;
import com.kach.studyhelperback.service.ArticlesRelationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticlesRelationsServiceImpl implements ArticlesRelationsService {
    @Autowired
    ArticleRepository articleRepository;

    @Autowired
    ArticlesRelationsRepository articlesRelationsRepository;

    @Override
    public void addRelation(Long from, Long to, Double weight) {
        Optional<Article> fromArticle = articleRepository.findById(from);
        Optional<Article> toArticle = articleRepository.findById(to);

        if (fromArticle.isEmpty() || toArticle.isEmpty()) {
            throw new IllegalArgumentException("Invalid article's id");
        }

        addRelation(fromArticle.get(), toArticle.get(), weight);
    }

    @Override
    public void addRelation(Article from, Article to, Double weight) {
        ArticlesRelations relation = new ArticlesRelations();
        relation.setFrom(from);
        relation.setTo(to);
        relation.setWeight(weight);

        articlesRelationsRepository.save(relation);
    }

    @Override
    public List<ArticlesRelations> getRelations(Article article, Integer depth) {
        List<ArticlesRelations> relationsFrom = articlesRelationsRepository.findAllByTo(article);
        List<ArticlesRelations> relationsTo = articlesRelationsRepository.findAllByFrom(article);

        relationsFrom.addAll(relationsTo);
        return relationsFrom;
    }

    @Override
    public List<ArticlesRelations> getRelations(Long articleId, Integer depth) {
        List<ArticlesRelations> relationsFrom = articlesRelationsRepository.findAllByTo_Id(articleId);
        List<ArticlesRelations> relationsTo = articlesRelationsRepository.findAllByFrom_Id(articleId);

        relationsFrom.addAll(relationsTo);
        return relationsFrom;
    }

    @Override
    public void useOrAddRelation(Article from, Article to, Double deltaWeight) {
        Optional<ArticlesRelations> optionalArticlesRelations = articlesRelationsRepository.findByFromAndTo(from, to);
        if (optionalArticlesRelations.isEmpty()) {
            addRelation(from, to, deltaWeight);
        }
        else {
            ArticlesRelations articlesRelations = optionalArticlesRelations.get();
            articlesRelations.setUsageCounter(articlesRelations.getUsageCounter() + 1);
            articlesRelations.setWeight(articlesRelations.getWeight() + deltaWeight);
            articlesRelationsRepository.save(articlesRelations);
        }
    }
}
