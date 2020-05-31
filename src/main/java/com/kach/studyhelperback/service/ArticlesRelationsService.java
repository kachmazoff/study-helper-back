package com.kach.studyhelperback.service;

import com.kach.studyhelperback.model.Article;
import com.kach.studyhelperback.model.ArticlesRelations;

import java.util.List;

public interface ArticlesRelationsService {
    /**
     * Создание связи между статьями (ориентированное ребро графа)
     * @param from ID статьи, которая является зависимостью
     * @param to ID статьи, которая является следствием
     * @param weight Вес связи
     */
    void addRelation(Long from, Long to, Double weight);

    /**
     * Создание связи между статьями (ориентированное ребро графа)
     * @param from Статья, которая является зависимостью
     * @param to Статья, которая является зависимостью
     * @param weight Вес связи
     */
    void addRelation(Article from, Article to, Double weight);

    /**
     * Получение связей для статьи
     * @param article Статья, для которой необходимо найти связные
     * @param depth Максимальная глубина связей
     * @return Список связей
     */
    List<ArticlesRelations> getRelations(Article article, Integer depth);

    /**
     * Получение связей для статьи
     * @param articleId ID статьи, для которой необходимо найти связные
     * @param depth Максимальная глубина связей
     * @return Список связей
     */
    List<ArticlesRelations> getRelations(Long articleId, Integer depth);

    /**
     * Использование связи или добавление новой
     * @param from Статья, которая является зависимостью
     * @param to Статья, которая является зависимостью
     * @param deltaWeight Изменение веса связи
     */
    void useOrAddRelation(Article from, Article to, Double deltaWeight);
    
}
