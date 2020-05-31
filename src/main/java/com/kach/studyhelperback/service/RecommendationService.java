package com.kach.studyhelperback.service;

import com.kach.studyhelperback.model.Article;
import com.kach.studyhelperback.model.User;

import java.util.List;

public interface RecommendationService {
    /**
     * Тестовый метод (желательно не использовать)
     * @return Список статей
     */
    List<Article> get();

    /**
     * Получение рекомендаций по статьям
     * @param user Пользователь, для которого необходимо сформировать рекомендацию
     * @return Список статей
     */
    List<Article> get(User user);

    /**
     * Получение рекомендованных статей для пользователя при просмотре конкретной статьи
     * @param user Пользователь, для которого необходимо дать рекомендацию
     * @param article Статья, которую просматривает пользователь
     * @return Список статей
     */
    List<Article> get(User user, Article article);

    /**
     * Получение рекомендованных статей для пользователя при просмотре конкретной статьи
     * @param user Пользователь, для которого необходимо дать рекомендацию
     * @param articleId ID статьи, которую просматривает пользователь
     * @return Список статей
     */
    List<Article> get(User user, Long articleId);

    /**
     * Получение самых просматриваемых статей за всё время
     * @return Список статей
     */
    List<Article> getTopArticles();

    /**
     * Получение актуальных статей
     * @return Список статей
     */
    List<Article> getHotArticles();
}
